package ebook.iak.compose.series1

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ebook.iak.compose.ui.theme.*

/**
 * @author Isaac Akakpo
 * Created on 11/29/2021 8:56 PM
 */

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun AnimatePositionWithInt() {


    var position by remember {
        mutableStateOf(0.dp)
    }
    //val xOffset = animateDpAsState(targetValue = position, tween(800,easing = LinearOutSlowInEasing))
    val xOffset = animateDpAsState(targetValue = position, spring(Spring.DampingRatioHighBouncy,Spring.StiffnessMedium))

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(width = 2.dp, shape = RoundedCornerShape(15), color = Blue)
            .background(color = Color.Transparent, shape = RoundedCornerShape(15))
    ) {

        var isLeft by remember {
            mutableStateOf(true)
        }

        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(100),
            backgroundColor = Green,
            onClick = {
                if (isLeft){
                    position = this.maxWidth - 165.dp
                    isLeft = false
                }else{
                    position = 0.dp
                    isLeft = true
                }
            },indication = rememberRipple(),
            modifier = Modifier
                .offset(x = xOffset.value, 0.dp)
                .padding(vertical = 15.dp, horizontal = 5.dp)
                .width(150.dp)
        ) {
            Text(
                text = if (isLeft) "Move To Right" else "Move To Left",
                textAlign = TextAlign.Center,
                color = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp)
            )
        }

    }
}