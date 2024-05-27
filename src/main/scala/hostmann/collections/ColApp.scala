package hostmann.collections

import hostmann.runTime

import scala.collection.mutable.ListBuffer


object ColApp extends App {

    /*val mis = "Mississippi"
    val res = getCharIndices(mis)
    for ( (key, value) <- res ) { println(s"$key: ${value.mkString(", ")} ") }


    val arr2 = ListBuffer[Int]()
    arr2.addAll(1 to 100000).toArray
    println(arr2.length)
    runTime { removeEachSecondQuick(arr2) }
    println(arr2.length)*/

    val names = Array("Tom", "Fred", "Harry", "Tom")
    val namesMap = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
    println(forth(names, namesMap).mkString("Array(", ", ", ")"))
    println(myMkString(names, "><"))
    for (i <- names.grouped(2)) println(i.mkString("Array(", ", ", ")"))

    println( java.util.TimeZone.getAvailableIDs.groupBy[java.util.TimeZone] )
}
