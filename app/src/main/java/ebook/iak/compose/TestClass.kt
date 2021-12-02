package ebook.iak.compose

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ebook.iak.compose.ui.theme.AnimatingComposeTheme

/**
 * @author Isaac
 * Created on 11/20/2021 9:39 PM
 */

@Preview(showBackground = true)
@Composable
fun TestModifierOffset() {
    val context = LocalContext.current

    AnimatingComposeTheme() {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        {
            val parentContainer = this
            var position by remember { mutableStateOf(0) }

            Box(modifier = Modifier
                .offset { IntOffset(position, 0) }
                .width(100.dp)
                .height(5.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(3.dp))
            ) {

            }

            var goFront by remember {
                mutableStateOf(position < (parentContainer.constraints.maxWidth - context.dpToPx(100)))
            }

            Button(onClick = {
                if (position < (parentContainer.constraints.maxWidth - context.dpToPx(100))) {
                    position += 10
                } else {
                    position = 0
                }

            }, modifier = Modifier.padding(top = 40.dp)) {
                Text(text = "Please to Move")
            }


            Text(
                text = "Max Width => ${parentContainer.constraints.maxWidth}",
                modifier = Modifier.padding(top = 100.dp)
            )


        }
    }


}

fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}