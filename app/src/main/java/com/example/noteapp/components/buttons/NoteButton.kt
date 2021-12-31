package com.example.noteapp.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        colors = colors,
        shape = RoundedCornerShape(7.dp),
        onClick = onClick
    ) {
        Text(text = text, style = TextStyle(color = Color.White))
    }

}