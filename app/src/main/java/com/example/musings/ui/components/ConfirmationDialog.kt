package com.example.musings.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmText: String = "Yes",
    dismissText: String = "No"
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        text = { Text(text = message) },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(dismissText)
            }
        }
    )
}
