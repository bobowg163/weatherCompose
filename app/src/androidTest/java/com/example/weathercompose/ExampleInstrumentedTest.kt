package com.example.weathercompose

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.weathercompose.data.now
import com.example.weathercompose.ui.screen.MainCard
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.weathercompose", appContext.packageName)
    }

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun horlyTest() {
        rule.setContent {
            val nowlist = mutableStateOf(
                now(
                    "2012-15-13",
                    "23",
                    "25",
                    "302",
                    "ssfe",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                )
            )
            MainCard(nowList =nowlist, onClickSync = { /*TODO*/ }, onClickSearch = { /*TODO*/ })
        }
    }
}