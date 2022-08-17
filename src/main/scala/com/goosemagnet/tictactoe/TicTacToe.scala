package com.goosemagnet.tictactoe

trait TicTacToe {
  def isOver: Boolean
  def makeMove(idx: Int): TicTacToe
  val toPlay: Player
}

