package com.example.lexynapse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lexynapse.Des
import com.example.lexynapse.R
import com.example.lexynapse.data.Difficultly
import com.example.lexynapse.data.WordCard
import com.example.lexynapse.ui.DifficultyState
import com.example.lexynapse.ui.LexiState
import com.example.lexynapse.ui.LexiViewModel
import com.example.lexynapse.ui.theme.LexynapseTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier,
               viewModel: LexiViewModel,
               navController: NavHostController,
             onFabClick:() -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val diffState by viewModel.difficultly.collectAsStateWithLifecycle()

    var selectedDifficultly by rememberSaveable {
        mutableIntStateOf(-1)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Lexynapse", fontWeight = FontWeight.Bold)
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedCard(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .defaultMinSize(minHeight = 100.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Word of the Day",
                        fontSize = 24.sp, fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Obscure", fontWeight = FontWeight.Bold)
                    Text("not discovered or known about; uncertain.")
                }
            }
            Text("Your Vocabulary",modifier = Modifier.padding(16.dp), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(painter = painterResource(R.drawable.filter), contentDescription = null)
                Difficultly.entries.forEach { level ->
                    ElevatedFilterChip(
                        selected = diffState.level == level.ordinal,
                        label = { Text(level.name) },
                        onClick = {
                                if (diffState.level == level.ordinal) viewModel.setDifficulty(-1)
                                else viewModel.setDifficulty(level.ordinal)
                        }
                    )
                }
            }
            Spacer(modifier= Modifier.padding(8.dp))
            WList(navController, uiState, diffState)
        }
    }
}

@Composable
fun WList(
    navController: NavHostController,
    lexiState: LexiState,
    diffState: DifficultyState
) {
    when(lexiState) {
        is LexiState.Empty -> {
            Box(modifier = Modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally)) {
                Text("Empty Please Add")
            }
        }
        is LexiState.Success -> {
            when(diffState.level) {
                -1 -> LazyColumn {
                    items(lexiState.words) { wordCard ->
                        WCard(onCardClick = {
                            navController.navigate(Des.Detail.name + "/${wordCard.id}")
                        }, word = wordCard, modifier = Modifier.padding(16.dp))
                    }
                }
                0 -> LazyColumn {
                    items(lexiState.words.filter { it.difficultly == Difficultly.Easy }) { wordCard ->
                        WCard(onCardClick = {
                            navController.navigate(Des.Detail.name + "/${wordCard.id}")
                        }, word = wordCard, modifier = Modifier.padding(16.dp))
                    }
                }
                1 -> LazyColumn {
                    items(lexiState.words.filter { it.difficultly == Difficultly.Medium }) { wordCard ->
                        WCard(onCardClick = {
                            navController.navigate(Des.Detail.name + "/${wordCard.id}")
                        }, word = wordCard, modifier = Modifier.padding(16.dp))
                    }
                }
                2 -> LazyColumn {
                    items(lexiState.words.filter { it.difficultly == Difficultly.Hard }) { wordCard ->
                        WCard(onCardClick = {
                            navController.navigate(Des.Detail.name + "/${wordCard.id}")
                        }, word = wordCard, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
@Composable
fun WCard(
    onCardClick: (WordCard) -> Unit,
    word: WordCard,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = {onCardClick(word)},
        modifier = modifier
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(word.name, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun WCardPreview() {
    LexynapseTheme {
        Surface {
            WCard(onCardClick = {}, word = WordCard(
                name = "Absurd",
                meaning = "Blatantly telling something",
                liked = true,
                difficultly = Difficultly.Medium
            ))
        }
    }
}
