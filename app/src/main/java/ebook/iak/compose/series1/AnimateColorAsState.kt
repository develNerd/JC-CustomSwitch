package ebook.iak.compose.series1

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ebook.iak.compose.ui.theme.Blue
import ebook.iak.compose.ui.theme.Green
import ebook.iak.compose.ui.theme.Orange
import ebook.iak.compose.ui.theme.White

/**
 * @author Isaac
 * Created on 11/24/2021 9:35 PM
 */

enum class CardColors(val color: Color,val colorName:String) { BLUE(color = Blue,colorName = "Blue"), ORANGE(color = Orange,colorName = "Orange"), GREEN(color = Green,colorName = "Green") }

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "ColorAsState")
@Composable
fun AnimateComposeColorAsState() {

    var isActive by remember {
        mutableStateOf(false)
    }

    val cardBackground = animateColorAsState(targetValue = if (isActive) Orange else White)

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(200.dp),
            onClick = { isActive = !isActive },
            backgroundColor = cardBackground.value,
            elevation = 3.dp, indication = rememberRipple()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Click to Change Color", modifier = Modifier.align(Alignment.Center))
            }
        }

    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun AnimateColorWithPalette() {

    var cardColor by remember {
        mutableStateOf(CardColors.BLUE.color)
    }

    var colorName by remember {
        mutableStateOf(CardColors.BLUE.colorName)
    }

    val cardBackground = animateColorAsState(targetValue = cardColor)

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(200.dp),
            backgroundColor = cardBackground.value,
            elevation = 3.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Color Name : $colorName",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Row(
            modifier = Modifier
                .wrapContentSize()
                .background(shape = RoundedCornerShape(100), color = Color.LightGray)
        ) {
            CardColors.values().forEach {
                ColorPaletteItem(backGroundColor = it, onClick = { paletteColor ->
                    cardColor = paletteColor.color
                    colorName = paletteColor.colorName
                })
            }
        }

    }

}

@ExperimentalMaterialApi
@Composable
fun ColorPaletteItem(backGroundColor: CardColors, onClick: (CardColors) -> Unit) {
    Card(
        modifier = Modifier
            .size(50.dp)
            .padding(5.dp),
        indication = rememberRipple(),
        shape = RoundedCornerShape(100),
        backgroundColor = backGroundColor.color,onClick = {onClick(backGroundColor)}
    ) {}
}

