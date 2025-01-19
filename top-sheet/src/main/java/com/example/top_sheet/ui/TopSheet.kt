package com.example.top_sheet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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

@OptIn(ExperimentalWearMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TopSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SwipeableState<SheetState> = rememberSwipeableState(initialValue = SheetState.Collapsed),
    sheetHeight: Dp = TopSheetDefaults.SheetMaxHeight,
    sheetMaxWidth: Dp = TopSheetDefaults.SheetMaxWidth,
    shape: Shape = TopSheetDefaults.ExpandedShape,
    containerColor: Color = TopSheetDefaults.ContainerColor,
    contentColor: Color = TopSheetDefaults.ContentColor,
    tonalElevation: Dp = TopSheetDefaults.Elevation,
    scrimColor: Color = TopSheetDefaults.ScrimColor,
    windowInsets: WindowInsets = TopSheetDefaults.windowInsets,
    dragHandle: @Composable (() -> Unit)? = { TopSheetDefaults.DragHandle() },
    properties: TopSheetProperties = TopSheetDefaults.properties(),
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier.fillMaxSize()) {
        val sheetHeightPx = with(LocalDensity.current) { sheetHeight.toPx() }
        val anchors = mapOf(
            (-sheetHeightPx - windowInsets.getTop(LocalDensity.current)) to SheetState.Collapsed,
            0f to SheetState.Expanded
        )

        if (sheetState.currentValue == SheetState.Expanded) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(scrimColor)
                .clickable(enabled = properties.shouldDismissOnBackPress,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    if (properties.shouldDismissOnBackPress) {
                        scope.launch {
                            sheetState.animateTo(SheetState.Collapsed)
                            onDismissRequest.invoke()
                        }
                    }
                })
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = sheetHeight)
                .widthIn(max = sheetMaxWidth)
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
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                content()
                Spacer(modifier = Modifier.weight(1f))
                if (dragHandle != null) {
                    Box(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        dragHandle()
                    }
                }
            }
        }
    }
}