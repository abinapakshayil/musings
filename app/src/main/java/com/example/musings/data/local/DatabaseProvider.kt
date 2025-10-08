package com.example.musings.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var INSTANCE: NoteDatabase? = null

    fun getDatabase(context: Context): NoteDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}