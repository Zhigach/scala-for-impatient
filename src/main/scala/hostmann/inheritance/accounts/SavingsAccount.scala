package hostmann.inheritance

class SavingsAccount(initialValue: Double) extends BankAccount(initialValue) {
    private var monthlyQuotaCount = 0

    private def increaseMonthlyCounter: Boolean = {
        if (monthlyQuotaCount < 3) { monthlyQuotaCount += 1; true }
        else false
    }

    override def withdraw(amount: Double): Double = { if increaseMonthlyCounter then super.withdraw(amount) else 0.0 }

    override def deposit(amount: Double): Double = { if increaseMonthlyCounter then super.deposit(amount) else 0.0}

    def earnMonthlyInterest(yearlyRate: Double ): Double = {
        monthlyQuotaCount = 0
        var delta = balance*( (1 + yearlyRate) / 100.0)
        balance += delta
        balance
    }
}
