package hostmann.traits

import java.awt.geom.Ellipse2D

trait RectangleLike {

    self: java.awt.geom.Ellipse2D =>

        def translate(dx: Int, dy: Int): Unit = {
            self.setFrame(self.getX + dx, self.getY + dy, self.getWidth, self.getHeight)
        }

        def grow(h: Int, v: Int): Unit = {
            self.setFrame(self.getX - h/2, self.getY - h/2, self.getWidth + h, self.getWidth + v)
        }    
}
/*
class Example {
    def main(args: Array[String]): Unit = {
        val egg = new Ellipse2D.Double(5, 10, 20, 30)
          with RectangleLike
        egg.translate(5, 10)
        egg.grow(16, 26)
    }
}*/

