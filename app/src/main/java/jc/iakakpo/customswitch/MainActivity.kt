package jc.iakakpo.customswitch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jc.iakakpo.customswitch.ui.theme.CustomSwitchTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CustomSwitchTheme {
                var checkStatus by remember {
                    mutableStateOf("Switch is Disabled")
                }
                Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
                    JCSwitch(size = 70.dp) {isChecked ->
                        if (isChecked){
                            checkStatus = "Switch is Enabled"
                        }else{
                            checkStatus = "Switch is Disabled"
                        }

                    }
                    Text(text = checkStatus,modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp),fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomSwitchTheme {
        Greeting("Android")
    }
}