package ebook.iak.compose.series3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author Isaac Akakpo
 * Created on 12/5/2021 4:59 PM
 */


@Composable
fun ClockAnim(){
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.size(300.dp)) {

        }
    }

}

@Composable
fun ClockAnimation(){

    Canvas(modifier = Modifier.fillMaxSize()){
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = Color.Blue,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 4
        )
    }

}