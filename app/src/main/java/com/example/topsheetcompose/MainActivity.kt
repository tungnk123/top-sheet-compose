package com.example.topsheetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.topsheetcompose.examples.TopSheetExample
import com.example.topsheetcompose.ui.theme.TopSheetComposeTheme

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