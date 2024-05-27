def signum(x: Double): Int = if (x > 0) 1 else if (x < 0) then -1 else 0

signum(11.0)
signum(0)
signum(-5)

def countdown(n: Int): Unit = {
    for (i <- (1 to n).reverse)
        print(i + " ")
}
countdown(5)

def unicodeProduct(s: String): Long = {
    var result: Long = 1
    for (ch <- s) result *= ch.toLong
    result
}
unicodeProduct("Hello")
// OR
"Hello".map(_.toLong).product

/*
def recUnicodeProduct(s: String, res: Int): Long = {
    var result: Long = 1
    if (s.length > 1) recUnicodeProduct(s.substring(0,s.length-1)) else
}*/

def recPower(x: Double, n: Int): Double = {
    if (n % 2 == 0 && n > 0) recPower(x, n / 2) * recPower(x, n / 2)
    else if (n % 2 == 1 && n > 0) x * recPower(x, n - 1)
    else if (n < 0) 1.0 / recPower(x, -n)
    else if (n == 0) 1.0
    else 0.0
}
recPower(3, -1)

