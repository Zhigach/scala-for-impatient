package hostmann.traits

import java.awt.geom.Ellipse2D

object TraitsApp extends App {
    val egg = new Ellipse2D.Double(5, 10, 20, 30)
        with RectangleLike
    egg.translate(5, 10)
    egg.grow(16, 26)

    val a: CryptoLogger = new CryptoLogger
    a.log("TEST")
    val b = new CryptoLogger(-3)
    b.log("TEST")
}
