package hostmann.objects

class Point(val x: Double, val y: Double) {

}

object Point {
    def apply(x: Double, y: Double): Point =
        new Point(x, y)

}
