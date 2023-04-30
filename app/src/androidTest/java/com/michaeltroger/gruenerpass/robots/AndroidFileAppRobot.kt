package com.michaeltroger.gruenerpass.robots

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import java.lang.NullPointerException

private const val RETRIALS = 3
private const val TIMEOUT = 5000L

class AndroidFileAppRobot {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val hamburgerSelector = By.desc("Show roots")
    private val rootDirSelector = By.textStartsWith("Android SDK")
    private val testDataDirSelector = By.text("testdata")

    fun goToPdfFolder() = apply {
        (1..RETRIALS).forEach { _ ->
            try {
                uiDevice.wait(Until.hasObject(hamburgerSelector), TIMEOUT)
                uiDevice.findObject(hamburgerSelector).click()

                uiDevice.wait(Until.hasObject(rootDirSelector), TIMEOUT)
                uiDevice.findObject(rootDirSelector).click()

                uiDevice.wait(Until.hasObject(testDataDirSelector), TIMEOUT)
                uiDevice.findObject(testDataDirSelector).click()

                return@apply
            } catch (e: NullPointerException) {
                //ignoring
            }
        }
    }

    fun selectRegularPdf(fileName: String): MainActivityRobot {
        selectPdf(fileName)
        return MainActivityRobot()
    }

    fun selectPasswordProtectedPdf(fileName: String): PasswordDialogRobot {
        selectPdf(fileName)
        return PasswordDialogRobot()
    }

    private fun selectPdf(fileName: String) {
        val selector = By.text(fileName)
        uiDevice.wait(Until.hasObject(selector), TIMEOUT)
        uiDevice.findObject(selector).click()
    }
}