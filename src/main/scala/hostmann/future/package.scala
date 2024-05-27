package hostmann

import java.net.URL
import scala.annotation.tailrec
import scala.collection.concurrent.TrieMap
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration.*
import scala.math.BigInt.int2bigInt
import scala.math.min
import scala.io.Source.fromURL

package object future {
    private val PASSWORD = "secret"
    private val CORES_NUM = Runtime.getRuntime.availableProcessors()

    def printThreadName[T]: Unit = {
        println(Thread.currentThread().getName)
    }

    private def getFromKeyboard(msg: String): String = {
        printThreadName
        println(msg)
        var res: String = readLine()
        while res.isEmpty do
            println(msg)
            res = readLine()
        res
    }

    def checkPassword(str: String): Boolean = {
        printThreadName
        println("Checking password")
        Thread.sleep(1000)
        str == PASSWORD
    }

    /**
     * Напишите функцию doInOrder, принимающую функции f: T =>
     * Future[U] и g: U => Future[V] и возвращающую функцию T =>
     * Future[U], которая для заданного значения t в конечном счете
     * возвращает g(f(t)).
     */
    def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] = {
        (t: T) => f(t).flatMap(u => g(u))
    }

    /**
     * Напишите функцию doTogether, принимающую функции f:
     * T => Future[U] и g: U => Future[V] и возвращающую функцию
     * T => Future[(U, V)], которая выполняет два задания параллель-
     * но и для заданного значения t в конечном счете возвращает
     * (f(t), g(t)).
     */
    def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] = {
        (t: T) => f(t).zip(g(t))
    }

    /**
     * Напишите функцию, принимающую последовательность объ-
     * ектов Future и возвращающую объект Future, который в конеч-
     * ном счете возвращает последовательность всех результатов.
     */
    def futureSequence[T](f: Future[T]*): Future[Seq[T]] = {
        Future.sequence(f)
    }

    /**
     * Напишите метод, который асинхронно продолжает выполнять action, пока не по-
     * лучит значения, соответствующего предикату until. Предикат
     * так же должен выполняться асинхронно. Протестируйте ме-
     * тод с функцией, принимающей пароль со стандартного ввода
     * в консоли, и функцией, имитирующей проверку допустимости
     * пароля сравнением его со строкой "secret" после секундной
     * задержки. Подсказка: используйте рекурсию.
     */
    @tailrec
    def repeat[T](action: => T, until: T => Boolean): Future[T] = {
        val f = Future(action)
        val input = Await.result(f, 5.seconds)
        if until(input) then f
        else repeat(action, until)
    }

    /**
     * Напишите программу, подсчитывающую количество простых
     * чисел между 1 и n с использованием BigInt.isProbablePrime.
     * Разбейте интервал на p частей, где p – количество доступных
     * процессоров (ядер). Подсчитайте количество простых чисел
     * в каждой части с помощью заданий Future, выполняющихся
     * параллельно, и объедините полученные результаты.
     */
    def primesInFutures(n: Int): Int = {
        val step: Int = n / CORES_NUM
        val ranges = for (i <- 1 to n by step) yield i until min((i + step).toInt, n)
        println(ranges.mkString("(", ",", ")"))
        var primes: Set[Int] = Set()

        val future = Future.traverse(ranges)(part =>
            Future {
                part.filter(n => n.bigInteger.isProbablePrime(1))
            }
        )

        val results = Await.result(future, 10.seconds)
        for (r <- results) for (elem <- r) primes = primes + elem.toInt
        println(primes.toArray.sorted.mkString("(", ", ", ")"))
        primes.size
    }

    /**
     * Напишите программу, которая предложит пользователю ввести
     * URL, прочитает веб-страницу с указанным URL и выведет
     * все гиперссылки. Используйте отдельный объект Future для
     * каждого из трех шагов.
     */
    private def extractURLs(url: String): Future[List[String]] = {
        val urlAddress =
            if url.isBlank then
                Future {
                    val u = getFromKeyboard("Enter URL:")
                    u
                }
            else
                Future(url)
        Await.result(urlAddress, 10.seconds)

        val content: Future[String] = Future {
            val bufferedSource = Try {
                val source = fromURL(urlAddress.value.get.get, "UTF-8")
                source.mkString
            }.get
            bufferedSource
        }
        Await.result(content, 30.seconds)

        println("Content received. Processing")
        val refs: Future[List[String]] = Future {
            val hrefTemplate = "<a.+?href=\"(.*?)\".*?>.*?</a>".r
            hrefTemplate.findAllMatchIn(content.value.get.get).map(m => m.group(1)).toList
        }
        Await.result(refs, 30.seconds)
        refs
    }

    /**
     * Напишите программу, которая предложит пользователю ввес­
     * ти URL, прочитает веб-страницу с указанным URL, найдет все
     * гиперссылки, посетит все их параллельно, извлечет из каждой
     * HTTP-заголовок Server и сообщит частоту встречаемости каж-
     * дого сервера. Задания, посещающие страницы, должны возвра-
     * щать заголовок.
     */
    def collectURLServer(url: String = ""): List[String] = {
        val refs = extractURLs(url)
        val servers = refs.value.get.get.map { l =>
            Future {
                val url: Option[URL] = Try(new URL(l)).toOption
                url match
                    case Some(u) => u.openConnection().getHeaderField("Server")
                    case None => "null"
            }
        }
        val serversFuture = Future.sequence(servers)
        Await.result(serversFuture, 30.seconds)

        val serversNameFrequency = serversFuture.value.get.get.groupMap(s => s)(s => System.currentTimeMillis().toInt)

        for (elem <- serversNameFrequency) { println(s"${elem._1} : ${elem._2.size}") }

        serversFuture.value.get.get
    }

    /**
     * Измените решение для предыдущего упражнения так, чтобы
     * задания, возвращающие заголовки, обновляли экземпляр Java-
     * класса ConcurrentHashMap или Scala-класса TrieMap. Это не так
     * просто, как кажется. Структуры данных с поддержкой исполь-
     * зования в многопоточном режиме гарантируют лишь, что их
     * нельзя повредить, но вы должны гарантировать атомарность
     * последовательности операций чтения и изменения.
     */
    def collectURLServerV2(url: String = ""): List[String] = {
        val refs = extractURLs(url)

        def increaseElementCount(map: TrieMap[String, Int], str: String): Unit = synchronized {
            map.updateWith(str) {
                case Some(v) => Option[Int](v + 1)
                case None => Option(0)
            }
            println(s"Map updated for key ${str}. Got ${map}")
        }

        val trieMap = TrieMap[String, Int]()

        val result = Future.traverse(refs.value.get.get) {
            link =>
                val serverHeader = Promise[String]()
                Future {
                    val url: Option[URL] = Try(new URL(link)).toOption
                    if url.isDefined then
                        val str = url.get.openConnection().getHeaderField("Server")
                        increaseElementCount(trieMap, str)
                        serverHeader.success(str)
                }
                serverHeader.future
        }

        Await.result(Future(result), 30.seconds)
        trieMap.keys.toList
    }

    /**
     * Используйте Promise для отмены задания. Разбейте заданный
     * диапазон больших целых чисел на несколько поддиапазонов
     * и выполните параллельный поиск палиндромных простых чи-
     * сел. При обнаружении такого числа установите его как значе-
     * ние объекта Future. Все задания должны периодически про-
     * верять, завершился ли объект Promise, и завершаться, если это
     * произошло.
     */
    def taskCancellation(num: BigInt): BigInt = {
        val step: BigInt = num / CORES_NUM
        val ranges = for (i <- 1 until num by step) yield Range.BigInt(i, num.min(i+step), 1)
        var palindromeFound = synchronized(false)

        val result = Promise[BigInt]()
        val firstPrime = Promise[Int]()

        ranges.foreach(part =>
            Future {
                var cur = part(0)
                while !result.isCompleted do
                    if isPalindrome(cur) then
                        result.success(cur)
                    else
                        cur += 1
                println(s"Exiting ${Thread.currentThread().getName}")
                Thread.currentThread().interrupt()
            }
        )
        Await.result(result.future, 30.seconds)
    }
    
    private def isPalindrome(n: BigInt): Boolean = {
        val str = n.toString
        val end: Int = str.length - 1
        val len: Int = str.length
        val numsTuples = for (i <- 0 to len / 2) yield (str(i.toInt), str(end - i.toInt)) //FIXME: without toInt "i" is counted as BigInt :shroud:
        numsTuples.find((f, l) => f != l) match
            case Some(v) => false
            case None => true
    }

}
