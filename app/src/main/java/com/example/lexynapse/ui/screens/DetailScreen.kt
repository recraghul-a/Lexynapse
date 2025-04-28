package com.example.lexynapse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lexynapse.data.WordCard
import com.example.lexynapse.ui.LexiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(itemId: Int, viewModel: LexiViewModel,
                 onBack: () -> Unit,
                 onDelClick: (WordCard?) -> Unit,
                 onShareClick: (WordCard?) -> Unit) {
    LaunchedEffect(key1 = itemId) {
        viewModel.getCard(itemId)
    }
    val word by viewModel.wordState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Details")},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {onDelClick(word)}) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                    IconButton(onClick ={onShareClick(word)}) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(word?.name ?: "No Data", fontSize = 24.sp)
                Text(word?.meaning ?: "No meaning found")
            }
        }
    }
}
