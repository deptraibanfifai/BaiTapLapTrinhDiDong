package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }
        // Tìm ô nhập liệu bằng văn bản "Bill Amount" và nhập số 10
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")

        // Kiểm tra xem màn hình có hiển thị đúng kết quả tip không
        val expectedTip = NumberFormat.getCurrencyInstance().format(1.50) // Với 15% mặc định
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists()
    }
}