package ebook.iak.compose.series1

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ebook.iak.compose.ui.theme.Green
import ebook.iak.compose.ui.theme.White

/**
 * @author Isaac Akakpo
 * Created on 11/28/2021 12:54 PM
 */

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun AnimateSizeWithDp() {


    val realCardSize = remember {
        mutableStateOf(100)
    }

    val stateCardSize = animateDpAsState(targetValue = realCardSize.value.dp )


    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
                .size(stateCardSize.value),
            backgroundColor = Green,
            elevation = 3.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Current Size : ", modifier = Modifier.align(Alignment.Center))
            }
        }

        val cardSizes = listOf(100, 300, 500)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            var currentIndex by remember {
                mutableStateOf(0)
            }

            cardSizes.forEachIndexed { cardIndex, cardSize ->
                CustomSwitch(
                    index = cardIndex,
                    isChecked = cardIndex == currentIndex,
                    size = cardSize
                ) { index, size ->
                    currentIndex = index
                    realCardSize.value = size
                }
            }
        }

    }


}

@ExperimentalMaterialApi
@Composable
fun CustomSwitch(
    index: Int,
    isChecked: Boolean,
    size: Int,
    onChecked: (index: Int, size: Int) -> Unit
) {

    val chipColour = animateColorAsState(targetValue = if (isChecked) Green else White)
    Card(
        backgroundColor = chipColour.value,
        elevation = 3.dp,
        shape = RoundedCornerShape(100),
        modifier = Modifier.padding(5.dp).toggleable(value = isChecked,onValueChange = {
            onChecked(index, size)
        })
    ) {
        Text(text = "$size.dp", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun CustomSwitchPreview() {

    val chipColour = animateColorAsState(targetValue = Green)
    Card(
        backgroundColor = chipColour.value,
        elevation = 3.dp,
        shape = RoundedCornerShape(100),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(text = "100.dp", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
    }
}