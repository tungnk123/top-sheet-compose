package com.example.top_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.SwipeableState
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.top_sheet.data.SheetState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun TopSheet(
    modifier: Modifier = Modifier,
    sheetContent: @Composable () -> Unit,
    sheetHeight: Dp,
    sheetState: SwipeableState<SheetState> = rememberSwipeableState(initialValue = SheetState.Collapsed),
    backgroundColor: Color = Color.White,
    scrimColor: Color = Color.Black.copy(alpha = 0.5f),
    enableDismissOnClickOutside: Boolean = true,
    onDismiss: (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier.fillMaxSize()) {
        val sheetHeightPx = with(LocalDensity.current) { sheetHeight.toPx() }
        val anchors = mapOf(
            -sheetHeightPx to SheetState.Collapsed, 0f to SheetState.Expanded
        )

        if (sheetState.currentValue == SheetState.Expanded) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(scrimColor)
                .clickable(enabled = enableDismissOnClickOutside,
                           indication = null,
                           interactionSource = remember { MutableInteractionSource() }) {
                    if (enableDismissOnClickOutside) {
                        scope.launch {
                            sheetState.animateTo(SheetState.Collapsed)
                            onDismiss?.invoke()
                        }
                    }
                })
        }

        Surface(modifier = Modifier
            .offset {
                IntOffset(
                    0, sheetState.offset.value.roundToInt()
                )
            }
            .swipeable(state = sheetState,
                       anchors = anchors,
                       orientation = Orientation.Vertical,
                       reverseDirection = false,
                       interactionSource = remember { MutableInteractionSource() })
            .zIndex(1f),
                color = backgroundColor) {
            Box(modifier = Modifier.height(sheetHeight)) {
                sheetContent()
            }
        }
    }
}