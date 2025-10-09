package com.example.musings.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musings.data.local.Note
import com.example.musings.ui.components.BottomNavScreen
import com.example.musings.ui.components.ConfirmationDialog
import com.example.musings.ui.components.EditNote
import com.example.musings.ui.components.NoteCard
import com.example.musings.viewmodel.NoteViewModel

@Composable
fun NotesListScreen(viewModel: NoteViewModel, navController: NavController, modifier: Modifier) {

    // Collect notes from the ViewModel
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    // Actual list
    val notesList = notes.value

    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    // No notes
    if (notesList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No notes yet.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 8.dp)
        ) {
            items(count = notesList.size) { index ->
                val note = notesList[index]
                NoteCard(
                    note = note,
                    onEditClick = { navController.navigate(
                        EditNote.createRoute(note.id, note.title, note.content)
                    ) },
                    onDeleteClick = { noteToDelete = note }
                )
            }
        }
    }




    // Show confirmation dialog if a note is selected for deletion
    noteToDelete?.let { note ->
        ConfirmationDialog(
            title = "Delete Note",
            message = "Are you sure you want to delete this note?",
            onConfirm = {
                viewModel.deleteNote(note)
                noteToDelete = null
            },
            onDismiss = { noteToDelete = null }
        )
    }
}