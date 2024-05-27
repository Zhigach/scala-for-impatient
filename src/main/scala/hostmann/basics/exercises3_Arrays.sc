import scala.collection.mutable.ArrayBuffer
import scala.util.Random

def randomArray(n: Int): ArrayBuffer[Int] =
    val result = new ArrayBuffer[Int]()
    for (i <- 0 to n) result += Random.between(-10, 10)
    result


def arr(n: Int): ArrayBuffer[Int] =
    val array = new ArrayBuffer[Int]()
    for (i <- 0 until n) array += i
    array
val array = arr(11)


val testArray = array.toArray


for (i <- 0 until testArray.length - 1)
    yield if i % 2 == 0 then testArray(i + 1) else testArray(i - 1)

for (i <- 1 until (testArray.length, 2))
    val holder = testArray(i)
    testArray(i) = testArray(i - 1)
    testArray(i - 1) = holder
testArray.mkString(",")

val ranArr = randomArray(10)

val dblArray = Array(1.2, 1.4, -2.5, 0.2)
dblArray.sum/dblArray.length

val testArray2 = randomArray(10)
testArray2.distinct

val testArray3 = randomArray(10)
println(testArray3)

val negInd = new ArrayBuffer[Int]()
for (index <- testArray3.indices if testArray3(index) < 0)
    negInd += index
for (i <- (negInd.length - 1 until 0 by -1))
    testArray3.remove(negInd(i))

println(testArray3)

