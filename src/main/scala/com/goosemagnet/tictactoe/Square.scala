package com.goosemagnet.tictactoe

sealed trait Square

case class Open(num: Int) extends Square {
  override def toString: String = num.toString
}

sealed trait Player extends Square {
  def opposite: Player
}

case object X extends Player {
  override def toString: String = "X"
  override def opposite: Player = O
}

case object O extends Player {
  override def toString: String = "O"
  override def opposite: Player = X
}
