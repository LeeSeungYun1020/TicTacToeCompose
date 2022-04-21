package com.leeseungyun1020.tictactoe.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.tictactoe.ViewModel.TicTacToeViewModel
import com.leeseungyun1020.tictactoe.ui.theme.TicTacToeTheme

val viewModel = TicTacToeViewModel()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    TicTaeToeScreen()
                }

            }
        }
    }
}

@Composable
fun TicTaeToeScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("Tic Tac Toe") }, actions = {
            IconButton(onClick = { viewModel.onResetSelected() }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Board()
            GameMessage(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun Board(modifier: Modifier = Modifier) {
    val boardState by viewModel.cells.observeAsState()
    Column(modifier = modifier) {
        for (x in 0..2) {
            Row {
                for (y in 0..2) {
                    OutlinedButton(
                        onClick = { viewModel.onCellSelected(x, y) },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                    ) {
                        Text(boardState?.get(x)?.get(y) ?: "", style = MaterialTheme.typography.h4)
                    }
                }
            }
        }
    }
}

@Composable
fun GameMessage(modifier: Modifier) {
    Row(modifier) {
        val turn by viewModel.order.observeAsState()
        val winner by viewModel.winner.observeAsState()
        val type = if (winner?.isNotBlank() == true) "Winner" else "Turn"
        val target = if (winner?.isNotBlank() == true) winner else turn
        Text(text = type, style = MaterialTheme.typography.h3)
        Text(text = target ?: "Error", style = MaterialTheme.typography.h3)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeTheme {
        TicTaeToeScreen()
    }
}