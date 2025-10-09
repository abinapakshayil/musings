package com.example.musings.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musings.data.local.Note
import com.example.musings.ui.screens.NotesListScreen
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.musings.ui.screens.AddNoteScreen
import com.example.musings.viewmodel.NoteViewModel


@Composable
fun NotesApp(navController: NavHostController, noteViewModel: NoteViewModel) {

    // BottomBar screens
    val bottomNavItems = listOf(
        BottomNavScreen.NotesList,
        BottomNavScreen.AddNote,
        BottomNavScreen.Settings,
        BottomNavScreen.Profile
    )

    // Observe the current backstack to highlight selected bottom nav item
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route


    // Show bottom bar only for top-level routes
    val topLevelRoutes = setOf(
        BottomNavScreen.AddNote.route,
        BottomNavScreen.Profile.route,
        BottomNavScreen.Settings.route
    )
    val showBottomBar = currentRoute in topLevelRoutes

    val topBarTitle = when (currentRoute) {
        BottomNavScreen.NotesList.route -> "Notes"
        BottomNavScreen.AddNote.route -> "Add Note"
        BottomNavScreen.Profile.route -> "Profile"
        BottomNavScreen.Settings.route -> "Settings"
        else -> ""
    }

    Scaffold(
        topBar = {
            GenericTopBar(
                title = topBarTitle,
                showBackButton = showBottomBar,
                onBackClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            NavigationBar {
                val currentRoute by navController.currentBackStackEntryAsState()
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(screen.icon, contentDescription = screen.label)
                        },
                        label = { Text(screen.label) },
                        selected = currentRoute?.destination?.route == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.NotesList.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavScreen.NotesList.route) {
                NotesListScreen(
                    viewModel = noteViewModel,
                    navController = navController,
                    modifier = Modifier.padding(8.dp),
                )
            }
            composable(
                BottomNavScreen.AddNote.route,
            ) {
                backStackEntry ->
                AddNoteScreen(
                    noteId = null,
                    initialTitle = "",
                    initialContent = "",
                    onNoteAdded = { id, title, content ->
                        val note = Note(id = id ?: 0, title = title, content =  content)
                        noteViewModel.addNote(note = note)
                        navController.popBackStack()
                    },
                    onCancelClick = { navController.popBackStack() }
                )
            }

            // Non-bottom-bar route (Edit)
            composable(
                route = EditNote.route,
                arguments = listOf(
                    navArgument("noteId") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType; defaultValue = "" },
                    navArgument("content") { type = NavType.StringType; defaultValue = "" }
                )
            ) { entry ->
                val noteId = entry.arguments?.getInt("noteId") ?: -1
                val title = entry.arguments?.getString("title") ?: ""
                val content = entry.arguments?.getString("content") ?: ""

                AddNoteScreen(
                    noteId = noteId,
                    initialTitle = title,
                    initialContent = content,
                    onNoteAdded = { id, title, content ->
                        val note = Note(id = id ?: 0, title = title, content =  content,)
                        noteViewModel.updateNote(note = note)
                        navController.popBackStack()
                    },
                    onCancelClick = { navController.popBackStack() }
                )
            }

            composable(BottomNavScreen.Profile.route) {
                TODO()
            }
            composable(BottomNavScreen.Settings.route) {
                TODO()
            }
        }
    }
}