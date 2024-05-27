class TestClass(num: Int, name: String) {
    val additionalNum: Int = 0
}

val a = TestClass(1, "Name")
a.additionalNum
//------
class Counter {
    private var counter = 0
    def increment(dx: Int): Unit =
        if (counter + dx > Int.MaxValue)
            counter -= Int.MaxValue + dx
        else
            counter += dx
    def value: Int = counter
}

val cnt = Counter()
cnt.value
cnt.increment(1)
cnt.value

//-----
class BankAccount(private var balance: Double) {
    def getBalance = balance // make field RO
    def deposit(value: Double): Unit = balance +=value
    def withdraw(value: Double): Double =
        if  (balance > value)
            balance -= value
            balance
        else
            printf(s"Insufficient money to withdraw ${value}." +
              s" Balance is ${balance}.\n")
            balance
}

val acc1 = BankAccount(10000.0)
acc1.getBalance
acc1.deposit(5000.0)
acc1.withdraw(14900.0)
acc1.withdraw(110)

//-----
class Time(val hours: Int, val minutes: Int) {
    def before(other: Time): Boolean =
        other.hours >= this.hours && other.minutes > this.minutes
}
val time1 = Time(16, 42)
val time2 = Time(13, 40)
time1.before(time2)
time2.before(time1)

//-----
class EnhancedTime(val hours: Int, val minutes: Int) {
    private val minutesFromZeros = hours * 60 + minutes
    def before(other: EnhancedTime): Boolean =
        this.minutesFromZeros < other.minutesFromZeros
}

//-----
class Person(name: String) {
    val names = name.strip.split(" ")
    val firstName = names(0)
    val lastName = names(1)
}

val person = Person("  John Galt ")
person.firstName
person.lastName