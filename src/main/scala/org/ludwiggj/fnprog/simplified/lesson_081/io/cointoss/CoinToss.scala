package org.ludwiggj.fnprog.simplified.lesson_081.io.cointoss

import scala.io.StdIn
import scala.util.Random

object CoinToss {
  def showPrompt(): Unit = {
    print("\n(h)eads, (t)ails, or (q)uit: ")
  }

  def getUserInput(): String = StdIn.readLine.trim.toUpperCase

  def printableFlipResult(flip: String) = flip match {
    case "H" => "Heads"
    case "T" => "Tails"
  }

  def printGameState(gameState: GameState): Unit = {
    println(s"#Flips: ${gameState.numFlips}, #Correct: ${gameState.numCorrect}")
  }

  def printGameState(printableResult: String, gameState: GameState): Unit = {
    print(s"Flip was $printableResult.")
    printGameState(gameState)
  }

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  def tossCoin(r: Random) = {
    val i = r.nextInt(2)
    i match {
      case 0 => "H"
      case 1 => "T"
    }
  }

  def createNewGameState(userInput: String,
                         coinTossResult: String,
                         gameState: GameState,
                         random: Random,
                         newNumFlips: Int): GameState = {
    if (coinTossResult == userInput) {
      GameState(newNumFlips, gameState.numCorrect + 1)
    } else {
      GameState(newNumFlips, gameState.numCorrect)
    }
  }

  def mainLoop(gameState: GameState, random: Random): IO[Unit] = for {
    _ <- IO { showPrompt() }
    userInput <- IO { getUserInput() }
    _ <- if (userInput == "H" || userInput == "T") for {
      // this first line is a hack;
      // a for-expression must begin with a generator
      _ <- IO { println("you said H or T") }
      coinTossResult = tossCoin(random)
      newNumFlips = gameState.numFlips + 1
      newGameState = createNewGameState(
        userInput,
        coinTossResult,
        gameState,
        random,
        newNumFlips
      )
      _ <- IO {
        printGameState(
          printableFlipResult(coinTossResult),
          newGameState
        )
      }
      _ <- mainLoop(newGameState, random)
    } yield Unit
    else for {
      _ <- IO { println("did not enter H or T") }
      _ <- IO { printGameOver() }
      _ <- IO { printGameState(gameState) }
    } yield Unit
  } yield Unit

  def main(args: Array[String]): Unit = {
    val s = GameState(0, 0)
    val r = new Random()
    mainLoop(s, r).run
  }
}
