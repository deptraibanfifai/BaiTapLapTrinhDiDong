package Lesson2

import java.util.Scanner

fun calculateSum(arr: IntArray): Int {
    var sum = 0
    for (item in arr) {
        sum += item
    }
    return sum
}


fun calculateAverage(arr: IntArray): Double {
    if (arr.isEmpty()) return 0.0
    return calculateSum(arr).toDouble() / arr.size
}
fun findMax(arr: IntArray): Int {
    var max = arr[0]
    for (item in arr) {
        if (item > max) max = item
    }
    return max
}


fun findMin(arr: IntArray): Int {
    var min = arr[0]
    for (item in arr) {
        if (item < min) min = item
    }
    return min
}


fun searchElement(arr: IntArray, target: Int): Int {
    for (index in arr.indices) {
        if (arr[index] == target) return index
    }
    return -1
}

fun main() {
    val reader = Scanner(System.`in`)

    print("Nhập số lượng phần tử của mảng: ")
    val n = reader.nextInt()
    val numbers = IntArray(n)

    println("Nhập các phần tử của mảng:")
    for (i in 0 until n) {
        print("Phần tử [$i]: ")
        numbers[i] = reader.nextInt()
    }


    println("KẾT QUẢ XỬ LÝ MẢNG")
    println("Mảng đã nhập: ${numbers.joinToString(", ")}")
    println("Tổng các phần tử: ${calculateSum(numbers)}")
    println("Trung bình cộng: ${calculateAverage(numbers)}")
    println("Phần tử lớn nhất: ${findMax(numbers)}")
    println("Phần tử nhỏ nhất: ${findMin(numbers)}")

    print("\nNhập giá trị cần tìm kiếm: ")
    val target = reader.nextInt()
    val index = searchElement(numbers, target)
    if (index != -1) {
        println("Giá trị $target được tìm thấy tại vị trí (index): $index")
    } else {
        println("Không tìm thấy giá trị $target trong mảng.")
    }
}