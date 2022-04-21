package com.leeseungyun1020.tictactoe.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leeseungyun1020.tictactoe.Model.Board

class TicTacToeViewModel: ViewModel() {
    val cells: MutableLiveData<List<List<String>>> = MutableLiveData(List(3) { List(3) {""} })
    val order: MutableLiveData<String> = MutableLiveData("X")
    val winner: MutableLiveData<String> = MutableLiveData("")
    val model = Board()

    fun onCellSelected(x: Int, y: Int) {
        model.mark(x, y)
        cells.postValue(model.cells.map { it.map { it.team.toString() } })
        order.postValue(model.turn.toString())
        winner.postValue(model.winner.toString())
    }

    fun onResetSelected() {
        model.restart()
        cells.postValue(model.cells.map { it.map { it.team.toString() } })
        order.postValue("X")
        winner.postValue("")
    }
}