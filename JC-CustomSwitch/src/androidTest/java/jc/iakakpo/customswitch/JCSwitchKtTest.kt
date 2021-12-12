package jc.iakakpo.customswitch

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.FileOutputStream

/**
 * @author Isaac Akakpo
 * Created on 12/12/2021 5:36 PM
 */
class JCSwitchKtTest{
    @get:Rule
    val composeTestRule = createComposeRule()




    @Test
    fun testSwitchAnimation(){

        //
        composeTestRule.setContent {
            JCSwitch(isChecked = false){

            }
        }
        val bitMapDisabled = composeTestRule.onRoot().captureToImage().asAndroidBitmap()
        composeTestRule.onNodeWithTag(CLICKABLE_BOX).performClick()
        composeTestRule.mainClock.advanceTimeBy(100L)
        saveScreenshot(testTagName,"test-${System.currentTimeMillis()}",bitMapDisabled)

        val bitMapEnabled = composeTestRule.onRoot().captureToImage().asAndroidBitmap()

        saveScreenshot(testTagName,"test-${System.currentTimeMillis()}",bitMapEnabled)

       bitMapDisabled.compare(bitMapEnabled)


        Thread.sleep(2000)

    }

    private fun saveScreenshot(folderName: String, filename: String, bmp: Bitmap) {
        val path = File(InstrumentationRegistry.getInstrumentation().targetContext.filesDir, folderName)
        if (!path.exists()) {
            path.mkdirs()
        }
        FileOutputStream("$path/$filename.webp").use { out ->
            bmp.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, out)
        }
        println("Saved screenshot to $path/$filename.webp")
    }

    private fun Bitmap.compare(other: Bitmap) {
        if (this.width != other.width || this.height != other.height) {
            throw AssertionError("Size of screenshot does not match golden file (check device density)")
        }
        // Compare row by row to save memory on device
        val row1 = IntArray(width)
        val row2 = IntArray(width)
        for (column in 0 until height) {
            // Read one row per bitmap and compare
            this.getRow(row1, column)
            other.getRow(row2, column)
            if (row1.contentEquals(row2)) {
                throw AssertionError("Sizes match but bitmap content has differences")
            }
        }
    }

    private fun Bitmap.getRow(pixels: IntArray, column: Int) {
        this.getPixels(pixels, 0, width, 0, column, width, 1)
    }



    companion object {
        private const val testTagName = "ComposeTests"

        @BeforeClass
        @JvmStatic
        fun clearExistingImagesBeforeStart() {
            clearExistingImages(testTagName)

        }

        private fun clearExistingImages(folderName: String) {
            val path = File(InstrumentationRegistry.getInstrumentation().targetContext.filesDir, folderName)
            path.deleteRecursively()
        }
    }
}


