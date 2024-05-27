package hostmann

import scala.collection.mutable.{ArrayBuffer, ListBuffer}


package object collections {

    /**
     * 1. Напишите функцию, возвращающую для указанной строки отображение индексов всех символов. Например, вызов
     * indexes("Mississippi") должен вернуть ассоциативный массив,
     * связывающий 'M' со множеством {0}, 'i' – со множеством {1,
     * 4, 7, 10} и т. д. Используйте изменяемый ассоциативный мас-
     * сив, отображающий символы в изменяемые множества. Как
     * обеспечить сортировку индексов в пределах множеств?
     */
    def getCharIndices(str: String): scala.collection.mutable.SortedMap[Char, scala.collection.mutable.SortedSet[Int]] = {
        //val map = str.view.map(c => c -> str.map.toSet).toMap
        val map = scala.collection.mutable.SortedMap[Char, scala.collection.mutable.SortedSet[Int]]()
        for (i <- 0 until str.length) {
            if map.contains(str.charAt(i)) then
                map(str.charAt(i)).add(i)
            else
                map(str.charAt(i)) = scala.collection.mutable.SortedSet[Int](i)
        }
        //val map = str.groupMap(ch => ch)(ch => )
        map
    }

    /**
     * 2. Реализуйте предыдущее упражнение с использованием неизменяемого ассоциативного массива символов в списки.
     */
    /*def getCharIndices2(str: String): Map[Char, Array[Int]] = {

        var map = Map[Char, Array[Int]]()
        for (i <- 0 until str.length) {
            if map.contains(str.charAt(i)) then
                map(str.charAt(i)) = map(str.charAt(i)) + i
            else
                map(str.charAt(i)) = scala.collection.mutable.SortedSet[Int](i)
        }
        map
    }*/

    /**
     * 3. Напишите функцию, удаляющую каждый второй элемент из
     * ListBuffer. Попробуйте два способа. Вызовите remove(i) для
     * всех четных индексов i, начиная с конца списка. Скопируйте
     * каждый второй элемент в новый список. Сравните произво-
     * дительность обоих способов
     *
     */
    def removeEachSecond(arr: ListBuffer[Int]): ListBuffer[Int] = {
        for (i <- (0 until arr.length - 1 by 2).reverse ) arr.remove(i)
        arr
    }
    def removeEachSecondQuick(arr: ListBuffer[Int]): ListBuffer[Int] = {
        val result = ListBuffer[Int]()
        for (i <- (0 until arr.length - 1 by 2).reverse) result.addOne(arr(i))
        result
    }

    /**
     * 4. Напишите функцию, принимающую коллекцию строк и ассо-
     * циативный массив, отображающий строки в целые числа. Она
     * должна возвращать коллекцию целых чисел, значения кото-
     * рых соответствуют строкам в ассоциативном массиве, повто-
     * ряющимся в исходной коллекции. Например, для Array("Tom",
     * "Fred", "Harry") и Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
     * функция должна вернуть Array(3, 5). Подсказка: используйте
     * flatMap для объединения значений типа Option, возвращаемых
     * методом get.
     */
    def forth(strings: Array[String], map: Map[String, Int]): Array[Int] = {
        val result = strings.flatMap( s => map.get(s))
        result
    }

    /**
     * 5. Реализуйте функцию, действующую подобно mkString, исполь-
     * зуя reduceLeft.
     */
    def myMkString(array: Array[String], separator: String = ", "): String = {
        array.reduceLeft( (prev, next) => prev + separator + next )
    }
}
