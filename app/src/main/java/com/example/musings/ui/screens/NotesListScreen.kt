package com.example.musings.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musings.data.Note
import com.example.musings.ui.components.NoteCard
import com.example.musings.viewmodel.NoteViewModel

@Composable
fun NotesListScreen( viewModel: NoteViewModel, modifier: Modifier) {

    // Collect notes from the ViewModel
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    // Actual list
    val notesList = notes.value


    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        items(count = notesList.size) { index ->
            val note = notesList[index]
            NoteCard(
                note = note,
                onEditClick = { TODO() },
                onDeleteClick = { TODO() }
            )
        }
    }
}