package hostmann.metch

object MatchApp extends App {
    /*val t2 = (1, 0)
    println(swap(t2))

    val arr = Array(1, 2, 3, 4, 5)
    println(swapArr(arr).mkString(", "))

    val multiple = Multiple(5, Article("Custom name", 25.99))
    println(multiple.price)
    val multipleBundles = Multiple(4, Bundle("Articles lot", Article("MMA", 15.99), Article("Scala Lang", 5.99)))
    println(multipleBundles.price)*/

    val tree = List(List(3, 8), 2, List(5))
    println(leafSum(tree))

    val binTree = Node(Leaf(1), Node(Leaf(4), Leaf(5)))
    println(leafSum(binTree))
}

