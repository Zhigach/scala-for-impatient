package hostmann.inheritance

object AccApp extends App{
    val acc1 = CheckingAccount(10000.0)
    println(acc1.currentBalance)
    acc1.withdraw(1000.0)
    println(acc1.currentBalance)
}
