package com.example.top_sheet.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.SecureFlagPolicy

@ExperimentalMaterial3Api
class TopSheetProperties(
    val securePolicy: SecureFlagPolicy,
    val isFocusable: Boolean,
    val shouldDismissOnBackPress: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TopSheetProperties) return false

        if (securePolicy != other.securePolicy) return false
        if (isFocusable != other.isFocusable) return false
        if (shouldDismissOnBackPress != other.shouldDismissOnBackPress) return false

        return true
    }

    override fun hashCode(): Int {
        var result = securePolicy.hashCode()
        result = 31 * result + isFocusable.hashCode()
        result = 31 * result + shouldDismissOnBackPress.hashCode()
        return result
    }
}
