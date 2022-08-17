package com.goosemagnet.tictactoe

import com.goosemagnet.tictactoe.TicTacToeImpl.startBoard


case class TicTacToeImpl(board: Seq[Square] = startBoard, toPlay: Player = X) extends TicTacToe {
  override def toString: String = {
    board.map(_.toString)
      .grouped(3)
      .map(_.mkString("|"))
      .mkString("\n")
  }

  override def isOver: Boolean = {
    val opponent = toPlay.opposite
    wonHorizontally(opponent) ||
      wonVertically(opponent) ||
      wonDiagonally(opponent)
  }

  def makeMove(idx: Int): TicTacToe = {
    if (idx < 0 || idx > board.size) this
    else {
      board(idx - 1) match {
        case Open(_) =>
          TicTacToeImpl(
            board.updated(idx - 1, toPlay),
            toPlay.opposite
          )
        case _ => this
      }
    }
  }

  private def wonHorizontally(player: Player): Boolean = {
    val rows = board.grouped(3)
    rows.exists { row =>
      row.forall(_.equals(player))
    }
  }

  private def wonVertically(player: Player): Boolean = {
    val rows = board.grouped(3).toList
    (0 to 2).exists { col =>
      (0 to 2).forall { row =>
        rows(row)(col).equals(player)
      }
    }
  }

  private def wonDiagonally(player: Player): Boolean = {
    val rows = board.grouped(3).toList
    (0 to 2).forall(num => rows(num)(num).equals(player)) ||
      (0 to 2).forall(num => rows(2 - num)(num).equals(player))
  }
}

object TicTacToeImpl {
  private val startBoard = {
    Seq(Open(1), Open(2), Open(3), Open(4), Open(5), Open(6), Open(7), Open(8), Open(9))
  }
}
