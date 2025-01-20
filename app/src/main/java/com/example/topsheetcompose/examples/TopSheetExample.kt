package com.example.topsheetcompose.examples

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.top_sheet.data.SheetState
import com.example.top_sheet.data.rememberTopSheetState
import com.example.top_sheet.ui.TopSheet
import com.example.topsheetcompose.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSheetExample(
    modifier: Modifier = Modifier
) {
    val topSheetState = rememberTopSheetState()
    val advancedTopSheetState = rememberTopSheetState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    scope.launch {
                        topSheetState.animateTo(SheetState.Expanded)
                    }
                }
            ) {
                Text("Show Simple Top Sheet")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    scope.launch {
                        advancedTopSheetState.animateTo(SheetState.Expanded)
                    }
                }
            ) {
                Text("Show Advanced Top Sheet")
            }
        }

        TopSheet(
            modifier = modifier,
            sheetHeight = 300.dp,
            topSheetState = topSheetState,
            onDismissRequest = {},
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    Text("This is a simple top sheet", Modifier.padding(16.dp))
                }
            },
        )

        TopSheet(
            modifier = modifier,
            sheetHeight = 500.dp,
            topSheetState = advancedTopSheetState,
            containerColor = Color.Black,
            contentColor = Color.Blue,
            scrimColor = Color.Black.copy(alpha = 0.7f),
            tonalElevation = 20.dp,
            isDragHandleVisible = true,
            onDismissRequest = {},
            dragHandle = {
                Box(
                    modifier = Modifier
                        .background(Color.Red, shape = RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_drag),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) {
                    Text("This is a advanced top sheet", Modifier.padding(16.dp))
                }
            },
        )
    }
}
