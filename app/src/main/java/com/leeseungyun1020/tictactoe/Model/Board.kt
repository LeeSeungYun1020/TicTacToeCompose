package com.leeseungyun1020.tictactoe.Model

enum class Team {
    X, O, U {
        override fun toString() = ""
    }
}

data class Cell(var team: Team, var count: Int = 0)

class Board {
    val cells = List(3) { List(3) { Cell(Team.U) } }
    var turn = Team.X
        private set
    var count = 0
        private set
    var winner = Team.U
        private set

    fun restart() {
        for (row in cells) {
            for (cell in row) {
                cell.team = Team.U
            }
        }
        turn = Team.X
        count = 0
        winner = Team.U
    }

    fun mark(x: Int, y: Int) {
        if (cells[x][y].team == Team.U && winner == Team.U) {
            count++
            cells[x][y].team = turn
            cells[x][y].count = count
            if (isWin(x, y))
                winner = turn
            this.turn = if (turn == Team.X) Team.O else Team.X
        }
    }

    private fun isWin(x: Int, y: Int): Boolean {
        return ((cells[x][0].team == turn && cells[x][1].team == turn && cells[x][2].team == turn) ||
            (cells[0][y].team == turn && cells[1][y].team == turn && cells[2][y].team == turn) ||
            (cells[0][0].team == turn && cells[1][1].team == turn && cells[2][2].team == turn) ||
            (cells[0][2].team == turn && cells[1][1].team == turn && cells[2][0].team == turn))
    }
}