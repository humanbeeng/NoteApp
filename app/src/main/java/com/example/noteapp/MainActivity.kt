package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.noteapp.screens.NoteScreen
import com.example.noteapp.screens.NoteViewModel
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NoteApp(noteViewModel)
                }
            }
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    val notes = noteViewModel.notesList.collectAsState().value
    NoteScreen(
        notes = notes,
        noteViewModel = noteViewModel,
        addNote = {
            noteViewModel.addNote(it)
        },
        deleteNote = {
            noteViewModel.deleteNote(it)
        },
        updateNote = {
            noteViewModel.updateNote(it)
        }


    )

}


//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun NoteScreenPreview() {
//    NoteScreen(notes = NoteDataSource().loadNotes(), onRemoveNote = {}, onAddNote = {})
//}