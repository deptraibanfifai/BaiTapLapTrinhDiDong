package Lesson2

import java.util.Scanner
import kotlin.math.sqrt

fun isPrime(n: Int): Boolean {
    if (n < 2) return false

    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    val reader = Scanner(System.`in`)

    print("Nhập một số nguyên để kiểm tra: ")
    // check đầu cuối
    if (reader.hasNextInt()) {
        val number = reader.nextInt()

        if (isPrime(number)) {
            println("$number là số nguyên tố.")
        } else {
            println("$number không phải là số nguyên tố.")
        }
    } else {
        println(" nhập số nguyên hợp lệ!")
    }
}