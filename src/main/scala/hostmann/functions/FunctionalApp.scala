package hostmann.functions

object FunctionalApp extends App {

    val arr = Array(4, 1, 6, -1, 0)

    println(maxValue(arr))

    println(values(x => x * x, -5, 5))

    //for (i <- 1 to 3) println(altFactorial(i))

    println( largest(x => 10 * x - x * x, 1 to 10) )
    println( maxPoint(x => 10 * x - x * x, 1 to 10) )

}
