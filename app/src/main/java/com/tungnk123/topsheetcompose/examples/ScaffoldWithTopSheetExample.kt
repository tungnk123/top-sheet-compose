package com.tungnk123.topsheetcompose.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tungnk123.top_sheet.data.SheetState
import com.tungnk123.top_sheet.data.rememberTopSheetState
import com.tungnk123.top_sheet.ui.TopSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopSheetExample(
    modifier: Modifier = Modifier
) {
    val topSheetState = rememberTopSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top Sheet Example") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
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
                        Text("Show Top Sheet")
                    }
                }
            }
        }
    )

    TopSheet(
        modifier = modifier,
        sheetHeight = 300.dp,
        topSheetState = topSheetState,
        containerColor = Color.Cyan,
        contentColor = Color.Black,
        scrimColor = Color.Black.copy(alpha = 0.7f),
        onDismissRequest = {
            scope.launch {
                topSheetState.animateTo(SheetState.Collapsed)
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                Text(
                    "This is a customizable top sheet",
                    Modifier.padding(16.dp)
                )
            }
        }
    )
}
