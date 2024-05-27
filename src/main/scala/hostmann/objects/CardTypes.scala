package hostmann.objects

object CardTypes extends Enumeration {
    val Hearts: Value = Value("♥")
    val Spades: Value = Value("♠")
    val Clubs: Value = Value("♣")
    val Diamonds: Value = Value("♦")
    def isRed(value: Value) = value == Hearts || value == Diamonds
    def isBlack(value: Value) = !isRed(value)
}
