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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.tictactoe.ui.theme.TicTacToeTheme

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
            IconButton(onClick = { /*TODO: 뷰모델에 클릭 요청*/ }) {
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
    // TODO: 뷰모델에서 상태 받아와 변경
    val boardState: List<List<String>> = List(3) { List(3) {""} }
    Column(modifier = modifier) {
        for (x in 0..2) {
            Row() {
                for (y in 0..2) {
                    OutlinedButton(
                        onClick = { /*TODO: 뷰모델에 클릭 처리 요청*/ },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                    ) {
                        Text(boardState[x][y], style = MaterialTheme.typography.h4)
                    }
                }
            }
        }
    }
}

@Composable
fun GameMessage(modifier: Modifier) {
    Row(modifier) {
        // TODO: 뷰모델에서 상태와 차례 받아와 변경
        Text(text = "Turn", style = MaterialTheme.typography.h3)
        Text(text = " X", style = MaterialTheme.typography.h3)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeTheme {
        TicTaeToeScreen()
    }
}