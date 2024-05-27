package hostmann.inheritance.items

import scala.collection.mutable.ArrayBuffer

class ItemBundle(args: Item*) {
    private[this] val bundle: ArrayBuffer[Item] = ArrayBuffer[Item]()

    def addItem(item: Item): Unit = bundle.addOne(item)

    def items: Array[Item] = bundle.toArray

    def priceMap: Double = {
        bundle.map( (i: Item) => i.price ).sum
    }

    def priceIter: Double = {
        var sum = 0.0
        for (i <- bundle)
            sum += i.price
        sum
    }
}
