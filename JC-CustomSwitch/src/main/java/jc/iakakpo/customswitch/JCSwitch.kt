package jc.iakakpo.customswitch

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import jc.iakakpo.customswitch.theme.Green200
import jc.iakakpo.customswitch.theme.SwitchBgDisabled


/**
 * @author Isaac Akakpo
 * Created on 12/6/2021 3:57 PM
 */

/*
* Custom Switch - Functionalities
*
* 1. Change Enabled  Color (Probably the disable color too) ..
* 2. Change box to card for clickable experience ..
* 3. Add vibration feature ..x
* 4. Add Switch Type (Square Switch, Cut Corner, Rounded)
* 5. Add Switch Text/Icon Functionality
* 6. elevate the round inner circle
*
*
* */

private enum class SwitchState {
    IS_ENABLED,
    NOT_ENABLED
}



@Composable
fun JCSwitch(
    modifier: Modifier = Modifier,
    enabledColor: Color = MaterialTheme.colors.primary,
    disabledColor: Color = SwitchBgDisabled,
    size: Dp = 50.dp,
    isChecked: Boolean = false,
    onCheckChanged: (Boolean) -> Unit
) {


    val switchSize by remember {
        mutableStateOf(size)
    }
    var currentState by remember { if (!isChecked) mutableStateOf(SwitchState.NOT_ENABLED) else  mutableStateOf(SwitchState.IS_ENABLED)}

    val bgDisabledColour by remember {
        mutableStateOf(disabledColor)
    }

    val bgEnabledColour by remember {
        mutableStateOf(enabledColor)
    }

    val transition = updateTransition(currentState, label = "SwitchState")
    val color by transition.animateColor(transitionSpec = {
        tween(200, easing = FastOutLinearInEasing)
    }, label = "") {
        when (it) {
            SwitchState.IS_ENABLED ->
                bgEnabledColour
            else -> bgDisabledColour
        }
    }
    val interactionSource = remember { MutableInteractionSource() }

    val clickable = Modifier.clickable(
        interactionSource = interactionSource,
        indication = null
    ) {
        currentState = if (currentState == SwitchState.NOT_ENABLED) {
            onCheckChanged(true)
            SwitchState.IS_ENABLED
        } else {
            onCheckChanged(false)
            SwitchState.NOT_ENABLED
        }
    }

    Box(
        modifier = Modifier
            .then(clickable)
            .testTag(CLICKABLE_BOX)
            .indication(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    radius = 100.dp,
                    color = Color.Transparent
                )
            )
    ) {
        BoxWithConstraints(
            modifier = modifier
                .width(switchSize - 3.dp)
                .height(switchSize / 2)
                .indication(MutableInteractionSource(), null)
                .background(color = color, shape = RoundedCornerShape(100)),
            contentAlignment = Alignment.CenterStart
        ) {

            val roundCardSize = this.maxWidth / 2

            val xOffset by transition.animateDp(
                transitionSpec = {
                    tween(150, easing = LinearOutSlowInEasing)
                }, label = "xOffset"
            ) { state ->
                when (state) {
                    SwitchState.NOT_ENABLED -> 0.dp
                    SwitchState.IS_ENABLED -> this.maxWidth - roundCardSize
                }
            }


            Card(
                modifier = Modifier
                    .size(this.maxWidth / 2)
                    .offset(x = xOffset, y = 0.dp)
                    .padding(3.dp), elevation = 3.dp,
                shape = RoundedCornerShape(100),
                backgroundColor = Color.White,
                border = BorderStroke(
                    if (currentState == SwitchState.NOT_ENABLED) 0.5.dp else 0.dp,
                    color = Color.LightGray
                )
            ) {


            }

        }
    }


}