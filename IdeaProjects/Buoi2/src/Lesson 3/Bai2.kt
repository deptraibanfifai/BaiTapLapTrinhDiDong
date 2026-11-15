package `Lesson 3`

open class Employee(val id: Int, val fullName: String, val baseSalary: Double) {

    open fun calculateSalary(): Double = baseSalary

    fun displayInfo() {
        println("ID: $id | Name: $fullName | Salary: ${calculateSalary()} VND")
    }
}

// Lớp con: Nhân viên văn phòng
class OfficeEmployee(id: Int, fullName: String, baseSalary: Double, val workingDays: Int)
    : Employee(id, fullName, baseSalary) {

    override fun calculateSalary(): Double {
        return baseSalary + workingDays * 200000
    }
}

// Lớp con: Nhân viên kinh doanh
class SalesEmployee(id: Int, fullName: String, baseSalary: Double, val salesAmount: Double)
    : Employee(id, fullName, baseSalary) {

    override fun calculateSalary(): Double {
        return baseSalary + salesAmount * 0.05
    }
}