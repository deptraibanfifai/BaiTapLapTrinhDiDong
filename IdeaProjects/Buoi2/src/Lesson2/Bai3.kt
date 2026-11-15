package Lesson2

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    var count = 0

    while (count < 3) {
        print("Enter the number of scores (at least 3): ")
        if (scanner.hasNextInt()) {
            count = scanner.nextInt()
            if (count < 3) {
                println("Please enter a number greater than or equal to 3.")
            }
        } else {
            println("Invalid input. Please enter an integer.")
            scanner.next()
        }
    }

    var sum = 0.0
    for (i in 1..count) {
        while (true) {
            print("Enter score #$i: ")
            if (scanner.hasNextDouble()) {
                val score = scanner.nextDouble()
                if (score in 0.0..100.0) {
                    sum += score
                    break
                } else {
                    println("Score must be between 0 and 100.")
                }
            } else {
                println("Invalid input. Please enter a number.")
                scanner.next()
            }
        }
    }

    val average = sum / count
    val grade = when {
        average >= 90 -> "A"
        average >= 80 -> "B"
        average >= 70 -> "C"
        average >= 60 -> "D"
        else -> "F"
    }

    println("Average: %.2f".format(average))
    println("Grade: $grade")
}
