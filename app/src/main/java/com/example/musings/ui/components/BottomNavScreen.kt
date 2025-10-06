package com.example.musings.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val label: String, val icon: ImageVector) {
    object NotesList : BottomNavScreen("notes_list", "Notes", Icons.Default.Home)
    object AddNote : BottomNavScreen("add_note", "Add", Icons.Default.Add)
    object Profile : BottomNavScreen("profile", "Profile", Icons.Default.Person)
    object Settings : BottomNavScreen("settings", "Settings", Icons.Default.Settings)
}

