package hostmann.metch

case class Bundle(description: String, item: Item*) extends Item {
    def price: Double = {
        var sum: Double = 0.0
        for (i <- item) sum += i.price
        sum
    }
}
