package hostmann

import hostmann.functions.FunctionalApp.arr

package object functions {

    /**
     * 1. Напишите функцию values(fun: (Int) => Int, low: Int, high:
     * Int), возвращающую коллекцию из значений в указанном диа-
     * пазоне. Например, вызов values(x => x * x, -5, 5) должен вер-
     * нуть коллекцию пар (-5, 25), (-4, 16), (-3, 9), ..., (5, 25).
     */
    def values(fun: (Int) => Int, low: Int, high: Int): Seq[(Int, Int)] = {
        for (i <- low to high) yield (i, fun(i))
    }

    /**
     * 2. Как получить наибольший элемент массива с помощью метода
     * reduceLeft?
     */
    def maxValue(arr: Array[Int]): Int = {
        arr.reduceLeft( (x, y) => math.max(x, y) )
        //arr.reduceLeft( (x, y) => math.max(x, y) )
    }

    /**
     * 3. Реализуйте функцию вычисления факториала с помощью ме-
     * тодов to и reduceLeft без применения цикла или рекурсии.
     */
    def altFactorial(n: Int): Int =
        //(1 to n).reduceLeft( (x1, x2) => x1 * x2 )
        //(1 to n).reduceLeft( (x1, x2) => x1*x2)
        (1 to n).foldLeft(1)( (x1, x2) => x1 * x2 )

    /**
     * Напишите функцию largest(fun: (Int) => Int, inputs: Seq[Int]),
     * возвращающую наибольшее значение функции внутри задан-
     * ной последовательности. Например, вызов largest(x => 10 * x
     *  – x * x, 1 to 10) должен вернуть 25. Не используйте цикл
     * или рекурсию.
     */
    def largest(fun: (Int) => Int, inputs: Seq[Int]): Int = {
        inputs.map(fun).max
    }

    /**
     * 6. Измените предыдущую функцию так, чтобы она возвращала
     * входное значение, соответствующее наибольшему выходному
     * значению. Например, вызов largestAt(fun: (Int) => Int, in­
     * puts: Seq[Int]) должен вернуть 5. Не используйте цикл или
     * рекурсию.
     *
     * @param fun
     * @param inputs
     * @return
     */
    def maxPoint( fun: (Int) => Int, inputs: Seq[Int]): Int = {
        //inputs.map( x => (x, fun(x)) ).maxBy(_._2)
        inputs.map( x => (x, fun(x)) ).maxBy(_._2)._1
    }

}
