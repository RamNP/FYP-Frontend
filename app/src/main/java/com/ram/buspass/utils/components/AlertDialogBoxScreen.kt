package com.ram.buspass.utils.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ram.buspass.ui.theme.Purple


@Composable
fun AlertDialogBox(
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    text: String,

    ) {
//    val activity = (LocalContext.current as Activity)
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() } // activity.finish()
            ) {
                Text(text = "Confirm")
            }
        },

        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(text = "Dismiss")
            }
        },
        title = { Text(text = title) },

        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(modifier = Modifier, color = Purple)
                Text(text = text , modifier = Modifier.padding(start = 8.dp))

            }
        }


    )
}
