package com.example.top_sheet.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.top_sheet.data.TopSheetProperties

@Stable
object TopSheetDefaults {
    /** The default shape for the TopSheet in its expanded state. */
    val ExpandedShape: Shape
        @Composable get() = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(0.dp),
            topEnd = CornerSize(0.dp)
        )

    /** The default container color for the TopSheet. */
    val ContainerColor: Color
        @Composable get() = MaterialTheme.colorScheme.surface

    /** The default content color for the TopSheet. */
    val ContentColor: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface

    /** The default elevation for the TopSheet. */
    val Elevation = 8.dp

    /** The default color of the scrim overlay for background content. */
    val ScrimColor: Color
        @Composable get() = Color.Black.copy(alpha = 0.5f)

    /**
     * The default maximum width for the TopSheet.
     */
    val SheetMaxWidth = 640.dp

    /**
     * The default maximum height for the TopSheet.
     */
    val SheetMaxHeight = 400.dp

    val windowInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars.only(WindowInsetsSides.Vertical)

    /**
     * The optional visual drag handle for the TopSheet.
     */
    @Composable
    fun DragHandle(
        modifier: Modifier = Modifier,
        width: Dp = 36.dp,
        height: Dp = 4.dp,
        shape: Shape = MaterialTheme.shapes.extraLarge,
        color: Color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Surface(
            modifier = modifier.padding(vertical = DragHandleVerticalPadding),
            color = color,
            shape = shape
        ) {
            Box(
                Modifier.size(width = width, height = height)
            )
        }
    }

    /**
     * Properties used to customize the behavior of a [TopSheet].
     *
     * @param securePolicy Policy for setting [WindowManager.LayoutParams.FLAG_SECURE] on the top
     * sheet's window.
     * @param isFocusable Whether the top sheet is focusable. When true,
     * the top sheet will receive IME events and key presses, such as when
     * the back button is pressed.
     * @param shouldDismissOnBackPress Whether the top sheet can be dismissed by pressing
     * the back button. If true, pressing the back button will call onDismissRequest.
     * Note that [isFocusable] must be set to true in order to receive key events such as
     * the back button - if the top sheet is not focusable then this property does nothing.
     */
    @OptIn(ExperimentalMaterial3Api::class)
    fun properties(
        securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
        isFocusable: Boolean = true,
        shouldDismissOnBackPress: Boolean = true
    ) = TopSheetProperties(securePolicy, isFocusable, shouldDismissOnBackPress)
}

private val DragHandleVerticalPadding = 22.dp
