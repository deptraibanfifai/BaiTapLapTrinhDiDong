package `Lesson 3`

class Student(val id: Int, val fullName: String, val gpa: Double) {

    // Hàm phân loại học lực
    fun getClassification(): String {
        return when {
            gpa >= 3.6 -> "Excellent"
            gpa >= 3.2 -> "Good"
            gpa >= 2.5 -> "Average"
            else -> "Poor"
        }
    }

    // Hàm hiển thị thông tin
    fun displayInfo() {
        println("ID: $id | Name: $fullName | GPA: $gpa | Rank: ${getClassification()}")
    }
}

fun main() {
    val sv = Student(1, "Nguyen Van A", 3.7)
    sv.displayInfo()
}