package hostmann.objects

object App extends App {

    println(Conversions.milesToKilometers(66))
    val p1 = Point(3, 4)
    //for (i <- CardTypes.values) println(i.toString)
    println(CardTypes.isRed(CardTypes.Diamonds))
    println(CardTypes.isRed(CardTypes.Hearts))
    println(CardTypes.isBlack(CardTypes.Hearts))
}


