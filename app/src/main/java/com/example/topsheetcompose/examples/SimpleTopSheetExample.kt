package com.example.topsheetcompose.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.top_sheet.data.SheetState
import com.example.top_sheet.data.rememberTopSheetState
import com.example.top_sheet.ui.TopSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopSheetExample(
    modifier: Modifier = Modifier
) {
    val topSheetState = rememberTopSheetState()
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier) {
        Button(
            onClick = {
                scope.launch {
                    topSheetState.animateTo(SheetState.Expanded)
                }
            }, modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        ) {
            Text("Show Top Sheet")
        }

        TopSheet(modifier = modifier,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    Text("This is a customizable top sheet", Modifier.padding(16.dp))
                }
            },
            sheetHeight = 300.dp,
            topSheetState = topSheetState,
            containerColor = Color.Red,
            contentColor = Color.Blue,
            scrimColor = Color.Black.copy(alpha = 0.7f),
            onDismissRequest = {})
    }
}
