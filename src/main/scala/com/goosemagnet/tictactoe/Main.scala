package com.goosemagnet.tictactoe

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.util.Try

object Main extends App {
  val game = new Game().init()
  game.unsafeRunSync()
}

class Game {
  def init(game: TicTacToe = TicTacToeImpl()): IO[Unit] = {
    for {
      _ <- printGame(game)
      idx <- getUserInput
      newGame = game.makeMove(idx)
      end <- handleEnd(newGame)
    } yield end
  }

  def getUserInput: IO[Int] = {
    for {
      _ <- IO.apply(print("Place on square: "))
      str <- IO.apply(Console.in.readLine())
      idx = Try(str.toInt).getOrElse(-1)
    } yield (idx)
  }

  def gameOver(game: TicTacToe): IO[Unit] = {
    for {
      _ <- IO.apply(println(game))
      _ <- IO.apply(println(s"${game.toPlay.opposite} wins!"))
    } yield ()
  }

  def handleEnd(game: TicTacToe): IO[Unit] = {
    if (game.isOver) gameOver(game)
    else init(game)
  }

  def printGame(game: TicTacToe): IO[Unit] = IO.apply(println(game))
}
