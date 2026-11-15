package com.example.tiptime


import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2.00) // Kết quả mong đợi là 2.00

        // Gọi hàm thực tế từ code của bạn
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, roundUp = false)

        // So sánh kết quả thực tế và mong đợi
        assertEquals(expectedTip, actualTip)
    }
}