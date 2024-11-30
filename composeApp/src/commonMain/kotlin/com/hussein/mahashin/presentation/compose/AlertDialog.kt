package com.hussein.mahashin.presentation.compose
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.hussein.mahashin.core.data.util.ColorBlack
import com.hussein.mahashin.core.data.util.ColorPrimary
import com.hussein.mahashin.core.data.util.ColorWhite
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.no
import mahashin.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    showDialog: MutableState<Boolean> // Pass the MutableState directly
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false }, // Important: Update state on dismiss
            title = { Text(text = title) },
            text = { Text(text = message) },
            backgroundColor = ColorWhite,
            confirmButton = {
                TextButton(
                    onClick = {
                        //showDialog.value = false  // Dismiss dialog before action
                        onConfirm()
                    },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, ColorWhite),  // Add border
                    colors = ButtonDefaults.textButtonColors(  // Use textButtonColors
                        contentColor = ColorWhite,
                        backgroundColor = ColorPrimary
                    ),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(text = stringResource(Res.string.yes), color = ColorWhite, fontSize = 14.sp)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, ColorWhite),  // Add border
                    colors = ButtonDefaults.textButtonColors(  // Use textButtonColors
                        contentColor = ColorWhite,
                        backgroundColor = ColorPrimary
                    ),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(text = stringResource(Res.string.no), color = ColorWhite, fontSize = 14.sp)
                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)

        )
    }
}