package hostmann.inheritance.items

object ItemApp extends App{
    val bundle: ItemBundle = ItemBundle( SimpleItem(2.0, "Test"), SimpleItem(3.0, "Test1") )

    val sampleItem = SimpleItem(10.0, "Test")
    for (i <- 0 to 1000000) bundle.addItem(sampleItem)

    var time = System.currentTimeMillis()
    bundle.priceMap
    var deltaTime = (System.currentTimeMillis() - time)/1000.0
    printf(f"Map execution time: $deltaTime%2.2f\n")
    time = System.currentTimeMillis()
    bundle.priceIter
    deltaTime = (System.currentTimeMillis() - time)/1000.0
    printf(f"Iteration execution time: $deltaTime%2.2f\n")

    assert(bundle.priceIter == bundle.priceMap)
}
