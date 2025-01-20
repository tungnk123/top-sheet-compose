package com.tungnk123.topsheetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tungnk123.topsheetcompose.examples.TopSheetExample
import com.tungnk123.topsheetcompose.ui.theme.TopSheetComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopSheetComposeTheme {
                TopSheetExample()
            }
        }
    }
}