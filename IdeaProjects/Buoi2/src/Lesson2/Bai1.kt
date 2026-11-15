package Lesson2

import java.util.Scanner

fun calculateSum(a: Int, b: Int): Int {
    return a + b
}

fun calculateDifference(a: Int, b: Int): Int = a - b

fun calculateProduct(a: Int, b: Int): Int = a * b

fun calculateQuotient(a: Int, b: Int): Double {
    if (b == 0) {
        println("Không thể chia cho 0)!")
        return 0.0
    }
    return a.toDouble() / b.toDouble()
}

fun main() {
    val reader = Scanner(System.`in`)

    println("CHƯƠNG TRÌNH")

    print("Nhập số nguyên t1: ")
    val num1 = reader.nextInt()

    print("Nhập số nguyên t2: ")
    val num2 = reader.nextInt()

    // Gọi các hàm và in kết quả
    println("\nKết quả:")
    println("Tổng ($num1 + $num2) = ${calculateSum(num1, num2)}")
    println("Hiệu ($num1 - $num2) = ${calculateDifference(num1, num2)}")
    println("Tích ($num1 * $num2) = ${calculateProduct(num1, num2)}")
    println("Thương ($num1 / $num2) = ${calculateQuotient(num1, num2)}")
}