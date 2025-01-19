package com.example.top_sheet.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.SwipeableState

@OptIn(ExperimentalWearMaterialApi::class)
class TopSheetState(
    initialValue: SheetState, private val confirmStateChange: (SheetState) -> Boolean = { true }
) {

    internal val swipeableState = SwipeableState(initialValue = initialValue)

    val currentValue: SheetState
        get() = swipeableState.currentValue

    suspend fun animateTo(targetValue: SheetState) {
        if (confirmStateChange(targetValue)) {
            swipeableState.animateTo(targetValue)
        }
    }

    suspend fun expand() = animateTo(SheetState.Expanded)
    suspend fun collapse() = animateTo(SheetState.Collapsed)
}

@Composable
fun rememberTopSheetState(
    initialValue: SheetState = SheetState.Collapsed,
    confirmStateChange: (SheetState) -> Boolean = { true }
): TopSheetState {
    return remember {
        TopSheetState(initialValue, confirmStateChange)
    }
}

