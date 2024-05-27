package hostmann.inheritance

class CheckingAccount(initialValue: Double) extends BankAccount(initialValue) {
    override def withdraw(amount: Double): Double = { balance -= amount + 1; balance }
}
