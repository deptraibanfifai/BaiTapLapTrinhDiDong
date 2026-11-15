package `Lesson 3`

// Lớp trừu tượng
abstract class BankAccount(val accountNumber: String, val ownerName: String, var balance: Double) {

    fun deposit(amount: Double) {
        balance += amount
        println("Đã nạp $amount vào tài khoản $accountNumber")
    }

    // Hàm trừu tượng: Mỗi loại tài khoản sẽ có cách rút tiền khác nhau
    abstract fun withdraw(amount: Double)

    fun displayInfo() {
        println("STK: $accountNumber | Chủ thẻ: $ownerName | Số dư: $balance")
    }
}

// Tài khoản tiết kiệm
class SavingAccount(accountNumber: String, ownerName: String, balance: Double)
    : BankAccount(accountNumber, ownerName, balance) {

    override fun withdraw(amount: Double) {
        if (balance - amount < 500000) {
            println("Lỗi: Tài khoản tiết kiệm phải duy trì ít nhất 500.000đ")
        } else {
            balance -= amount
            println("Rút thành công $amount")
        }
    }
}

// Tài khoản thanh toán (được phép thấu chi)
class CheckingAccount(accountNumber: String, ownerName: String, balance: Double)
    : BankAccount(accountNumber, ownerName, balance) {

    override fun withdraw(amount: Double) {
        if (balance - amount < -2000000) {
            println("Lỗi: Vượt quá hạn mức thấu chi (-2tr)")
        } else {
            balance -= amount
            println("Rút thành công $amount")
        }
    }
}

// Hàm Main thực thi
fun main() {
    val accounts = listOf(
        SavingAccount("SAV001", "Tran Van B", 1000000.0),
        CheckingAccount("CHE002", "Le Thi C", 500000.0)
    )

    for (acc in accounts) {
        acc.displayInfo()
        acc.withdraw(700000.0) // Thử rút tiền
        acc.displayInfo()
        println("--------------------")
    }
}