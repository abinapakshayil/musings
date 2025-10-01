package com.example.musings.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musings.data.Note
import com.example.musings.ui.components.NoteCard

@Composable
fun NotesListScreen(notes: List<Note>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        items(count = notes.size) { index ->
            val note = notes[index]
            NoteCard(
                note = note,
                onEditClick = { TODO() },
                onDeleteClick = { TODO() }
            )
        }
    }

}