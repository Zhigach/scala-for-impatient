package hostmann.traits

class CryptoLogger(val shift: Int = 3) extends ConsoleLogger {    

    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val lowerBound = alphabet(0)
    val upperBound = alphabet.last
    val range = lowerBound to upperBound

    def substituteChar(c: Char): Char = {
        val shiftedC: Int = c - shift
        shift > 0 match
            case true =>
                if shiftedC >= lowerBound then
                    shiftedC.toChar
                else
                    (upperBound - (lowerBound - shiftedC)).toChar
            case false =>
                if shiftedC <= upperBound then
                    shiftedC.toChar
                else
                    (lowerBound + (shiftedC - upperBound)).toChar

    }

    def cipher(msg: String): String = {
        val result: StringBuilder = new StringBuilder()
        for (c <- msg) result.append(substituteChar(c))
        result.toString()
    }

    override def log(msg: String): Unit = super.log(cipher(msg))

    
    
    def main(args: Array[String]): Unit = {
        val a: CryptoLogger = new CryptoLogger
        a.log("TEST")
        val b = new CryptoLogger(-3)
        b.log("TEST")
    }
}
