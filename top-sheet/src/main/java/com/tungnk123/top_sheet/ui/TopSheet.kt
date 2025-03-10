package com.tungnk123.top_sheet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.wear.compose.material.swipeable
import com.tungnk123.top_sheet.data.SheetState
import com.tungnk123.top_sheet.data.TopSheetProperties
import com.tungnk123.top_sheet.data.TopSheetState
import com.tungnk123.top_sheet.data.rememberTopSheetState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TopSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    topSheetState: TopSheetState = rememberTopSheetState(),
    sheetHeight: Dp = TopSheetDefaults.SheetMaxHeight,
    sheetMaxWidth: Dp = TopSheetDefaults.SheetMaxWidth,
    shape: Shape = TopSheetDefaults.ExpandedShape,
    containerColor: Color = TopSheetDefaults.ContainerColor,
    contentColor: Color = TopSheetDefaults.ContentColor,
    tonalElevation: Dp = TopSheetDefaults.Elevation,
    scrimColor: Color = TopSheetDefaults.ScrimColor,
    windowInsets: WindowInsets = TopSheetDefaults.windowInsets,
    dragHandle: @Composable (() -> Unit)? = { TopSheetDefaults.DragHandle() },
    isDragHandleVisible: Boolean = TopSheetDefaults.IsDragHandleVisible,
    properties: TopSheetProperties = TopSheetDefaults.properties(),
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier.fillMaxSize()) {
        val sheetHeightPx = with(LocalDensity.current) { sheetHeight.toPx() }
        val anchors = mapOf(
            (-sheetHeightPx - windowInsets.getTop(LocalDensity.current)) to SheetState.Collapsed,
            windowInsets.getTop(LocalDensity.current).toFloat() to SheetState.Expanded
        )

        if (topSheetState.currentValue == SheetState.Expanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(scrimColor)
                    .clickable(
                        enabled = properties.shouldDismissOnBackPress,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (properties.shouldDismissOnBackPress) {
                            scope.launch {
                                topSheetState.collapse()
                                onDismissRequest.invoke()
                            }
                        }
                    }
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = sheetHeight)
                .widthIn(max = sheetMaxWidth)
                .offset {
                    IntOffset(
                        0,
                        topSheetState.swipeableState.offset.value.roundToInt()
                    )
                }
                .swipeable(
                    state = topSheetState.swipeableState,
                    anchors = anchors,
                    orientation = Orientation.Vertical,
                    reverseDirection = false,
                    interactionSource = remember { MutableInteractionSource() }
                )
                .zIndex(1f),
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    content()
                }
                if (isDragHandleVisible && dragHandle != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        dragHandle()
                    }
                }
            }
        }
    }
}
