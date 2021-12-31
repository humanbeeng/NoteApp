package com.example.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.buttons.NoteButton
import com.example.noteapp.components.inputs.NoteTextInput
import com.example.noteapp.models.Note


@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    addNote: (Note) -> Unit,
    deleteNote: (Note) -> Unit,
    updateNote: (Note) -> Unit,
    noteViewModel: NoteViewModel
) {
    var noteTitle by remember {
        mutableStateOf("")
    }

    var noteText by remember {
        mutableStateOf("")
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            backgroundColor = Color.LightGray,
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notifications Icon"
                )

            })

        NoteTextInput(
            modifier = Modifier.padding(6.dp),
            label = "Title",
            text = noteTitle,
            onTextChange = {
                if (it.all { individualChar -> individualChar.isWhitespace() || individualChar.isLetter() }) {
                    noteTitle = it
                }
            })


        NoteTextInput(
            modifier = Modifier.padding(6.dp),
            label = "Text",
            text = noteText,
            onTextChange = {
                if (it.all { individualChar -> individualChar.isWhitespace() || individualChar.isLetter() }) {
                    noteText = it
                }
            })

        val context = LocalContext.current

        NoteButton(
            text = noteViewModel.buttonState!!,
            modifier = Modifier.padding(top = 10.dp),
            onClick = {
                if (noteTitle.isNotEmpty() && noteText.isNotEmpty() ) {
                    addNote(
                        Note(text = noteText, title = noteTitle)
                    )
                    noteTitle = ""
                    noteText = ""

                    Toast.makeText(context, "Note added", Toast.LENGTH_LONG).show()
                } else Toast.makeText(context, "Notes field cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            }
        )

        Divider(modifier = Modifier.padding(top = 6.dp, bottom = 4.dp))

        LazyColumn {
            items(notes) { note ->
                NotesRow(
                    note,
                    onClicked = deleteNote,
                    onUpdateNote = {
                        noteText = it.text
                        noteTitle = it.title
                        noteViewModel.buttonState = "Update Note"
                        updateNote(it)
                    }
                )
            }
        }


    }
}


@Composable
fun NotesRow(
    note: Note,
    onClicked: (Note) -> Unit,
    onUpdateNote: (Note) -> Unit
) {

    Surface(
        shape = RoundedCornerShape(5.dp),
        color = Color.LightGray,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onUpdateNote(note) }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onClicked(note)
                }

        ) {

            Text(text = note.title, style = MaterialTheme.typography.h6)
            Text(text = note.text)
            Text(text = note.entryDate.toString())
//            Text(text = )

        }

    }
}