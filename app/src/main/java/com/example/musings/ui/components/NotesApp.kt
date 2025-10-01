package com.example.musings.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musings.data.Note
import com.example.musings.ui.screens.NotesListScreen

@Composable
fun NotesApp() {
    val sampleNotes = listOf(
        Note(title = "Grocery List", content = "Buy milk, eggs, and bread"),
        Note(title = "Workout Plan", content = "Pushups, squats, and running"),
        Note(title = "Project Ideas", content = "Build a notes app in Jetpack Compose"),
        Note(title = "Grocery List", content = "Buy milk, eggs, and bread"),
        Note(title = "Workout Plan", content = "Pushups, squats, and running"),
        Note(title = "Project Ideas", content = "Build a notes app in Jetpack Compose"),
        Note(title = "Grocery List", content = "Buy milk, eggs, and bread"),
        Note(title = "Workout Plan", content = "Pushups, squats, and running"),
        Note(title = "Project Ideas", content = "Build a notes app in Jetpack Compose"),
        Note(title = "Grocery List", content = "Buy milk, eggs, and bread"),
        Note(title = "Workout Plan", content = "Pushups, squats, and running"),
        Note(title = "Project Ideas", content = "Build a notes app in Jetpack Compose"),
        Note(title = "Grocery List", content = "Buy milk, eggs, and bread"),
        Note(title = "Workout Plan", content = "Pushups, squats, and running"),
        Note(title = "Project Ideas", content = "Build a notes app in Jetpack Compose")
    )

    Scaffold(
        topBar = {
            GenericTopBar(
                title = "Notes",
                onBackClick = { /* For main screen, maybe finish() or do nothing */ }
            )
        },
        content = { paddingValues ->
            // Pass padding from scaffold to content for proper spacing
            NotesListScreen(
                notes = sampleNotes,
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}