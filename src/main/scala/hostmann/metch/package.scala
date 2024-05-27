package hostmann

package object metch {

    /**
     2. Используя сопоставление с образцом, напишите функцию
     swap, которая принимает пару целых чисел и возвращает ту
     же пару, поменяв компоненты местами.
     */
    def swap(tuple2: (Int, Int)): (Int, Int) = {
        tuple2 match {
            case (a, b) => (b, a)
        }
    }

    /**
     * 3. Используя сопоставление с образцом, напишите функцию
     * swap, которая меняет местами первые два элемента массива,
     * если он имеет длину не меньше двух.
     */
    def swapArr(arr: Array[Int]): Array[Int] = {
        arr match {
            case Array(a, b, rest*) => Array(b, a) ++ rest
        }
    }

    /**
     * 5. Для представления деревьев, хранящих значения только в листь­
     * ях, можно использовать списки. Например, список ((3 8) 2 (5))
     * В этом случае одни элементы списка будут числами, а дру-
     * гие – списками. Однако в Scala нельзя создавать разнородные
     * списки, поэтому придется использовать List[Any]. Напишите
     * функцию leafSum для вычисления суммы всех значений листь­
     * ев, используя сопоставление с образцом для отделения чисел
     * от списков.

     */
    def leafSum(list: List[Any]): Int = {
        var sum: Int = 0
        for (i <- list) {
            sum += {
                i match {
                    case l: List[Int] => l.sum
                    case integer: Int => integer
                    case _ => 0
                }
            }
        }
        sum
    }

    /**
     * Такие деревья лучше всего моделировать с применением case-
     * классов. Начните с бинарных деревьев.
     * Напишите функцию, вычисляющую сумму всех значений
     * листьев­.
     */
    def leafSum(binaryTree: BinaryTree): Int = {
        var sum: Int = 0
        binaryTree match
            case leaf: Leaf => sum += leaf.value
            case node: Node => sum += leafSum(node.left) + leafSum(node.right)
        sum
    }


}
