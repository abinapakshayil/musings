package com.example.musings.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTopBar(
    title: String,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(
                    onClick = { onBackClick() },
                    modifier = Modifier
                        .size(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Thin line separator
        HorizontalDivider(
            Modifier,
            thickness = 1.dp,
            color = DividerDefaults.color
        )
    }
}