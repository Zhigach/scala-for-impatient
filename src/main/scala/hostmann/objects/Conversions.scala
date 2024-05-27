package hostmann.objects

object Conversions {
    val cmPerInch = 2.54
    val LPerGallon = 4.54609
    val kmPerMile = 1.609344
    
    def inchesToCentimeters(inches: Double): Double =
        inches * cmPerInch
        
    def gallonsToLiters(gallons: Double): Double =
        gallons * LPerGallon
        
    def milesToKilometers(miles: Double): Double =
        miles * kmPerMile
}


