package com.example.lexynapse

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lexynapse.ui.GenViewModel
import com.example.lexynapse.ui.LexiViewModel
import com.example.lexynapse.ui.screens.AddScreen
import com.example.lexynapse.ui.screens.DetailScreen
import com.example.lexynapse.ui.screens.MainScreen
enum class Des {
    Home, Add, Detail
}

@Composable
fun HomeScreen(
    lexiViewModel: LexiViewModel = viewModel(factory = LexiViewModel.Factory),
    genViewModel: GenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController, startDestination = Des.Home.name) {
        composable(route = Des.Home.name) {
            MainScreen(viewModel = lexiViewModel, navController = navController) {
                navController.navigate(Des.Add.name)
            }
        }
        composable(route = Des.Add.name) {
            AddScreen(onNavBack = {
                navController.popBackStack()
            }) {
                word ->
                lexiViewModel.insertWord(word).also {
                    navController.popBackStack()
                }
            }
        }
        composable(route = Des.Detail.name + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.IntType
            })
        ) {
            backStackEntry ->
            val itemId:Int = backStackEntry.arguments?.getInt("itemId")!!
            DetailScreen(itemId, lexiViewModel, genViewModel,onBack = {
                navController.popBackStack()
            }, onDelClick = { wordCard ->
                if (wordCard != null) {
                    lexiViewModel.deleteWord(wordCard).also {
                        navController.popBackStack()
                    }
                } else {
                    Toast.makeText(context, "Cant Do this Try later", Toast.LENGTH_SHORT).show()
                }
            }, onShareClick = {
                word -> shareText(context, word?.name ?: "No word")

            })
        }
    }
}
fun shareText(context: Context, textToShare: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, textToShare)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(shareIntent)
}