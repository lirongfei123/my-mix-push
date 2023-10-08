package com.mixpush.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mixpush.core.GetRegisterIdCallback
import com.mixpush.core.MixPushClient
import com.mixpush.core.MixPushPlatform
import com.mixpush.example.ui.theme.MyMixPushTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 开启日志
        MixPushClient.getInstance().setPushReceiver(MyPushReceiver())
        MixPushClient.getInstance().register(this)
        setContent {
            MyMixPushTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MixPushClient.getInstance()
                        .getRegisterId(this, object : GetRegisterIdCallback() {
                            override fun callback(platform: MixPushPlatform?) {
                                if (platform != null) {
                                    Log.e("GetRegisterIdCallback", platform.toString())
                                    // TODO 上报regId给服务端
                                }
                            }
                    });
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMixPushTheme {
        Greeting("Android")
    }
}