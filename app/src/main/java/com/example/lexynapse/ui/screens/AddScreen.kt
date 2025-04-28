package com.example.lexynapse.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lexynapse.data.Difficultly
import com.example.lexynapse.data.Word
import com.example.lexynapse.ui.theme.LexynapseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onNavBack: () -> Unit,
    onAdd: (Word) -> Unit
) {
    val context = LocalContext.current
    var word by rememberSaveable {
        mutableStateOf("")
    }
    var meaning by rememberSaveable {
        mutableStateOf("")
    }
    var diffLevel by rememberSaveable {
        mutableStateOf<Difficultly?>(null)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Add Word")
            },
                navigationIcon = {
                    IconButton(onClick = onNavBack) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }
                }
            )

        }
    ) {
        innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                OutlinedTextField(value = word,
                    onValueChange = {word = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    singleLine = true,
                    label = {
                        Text("Type the word")
                    }
                    )
                OutlinedTextField(value = meaning,
                    onValueChange = {meaning = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = { Text("Enter the meaning of the word")},
                    minLines = 2)

                ElevatedCard(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround) {
                        Difficultly.entries.forEach {
                            FilterChip(selected = diffLevel == it,
                                onClick = {
                                    diffLevel = it
                                },
                                label = {Text(it.name)})
                        }
                    }
                }
                ElevatedButton(onClick = {
                    if (word.isEmpty() || meaning.isEmpty() || diffLevel == null) {
                        Toast.makeText(context,"Cannot Add", Toast.LENGTH_SHORT).show()
                    } else {
                        onAdd(
                            Word(name = word, meaning = meaning, difficultly = diffLevel ?: Difficultly.Easy)
                        )
                    }
                },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth().padding(16.dp)
                       ) {
                    Text("Add")
                }
            }
    }
}

@Preview
@Composable
private fun AddScreenPreview() {
    LexynapseTheme {
        Surface {
            AddScreen({}) { }
        }
    }
}