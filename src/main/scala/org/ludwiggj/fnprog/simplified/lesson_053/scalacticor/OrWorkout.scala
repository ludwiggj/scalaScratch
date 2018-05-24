package org.ludwiggj.fnprog.simplified.lesson_053.scalacticor

import org.scalactic._

object OrWorkout {
  def main(args: Array[String]): Unit = {
    def makeInt(s: String): Int Or ErrorMessage = {
      try {
        Good(s.trim.toInt)
      } catch {
        case e: Exception => Bad(e.toString)
      }
    }

    println(makeInt("1"))
    println(makeInt("boo"))

    makeInt("11") match {
      case Good(i) => println("Answer: " + i)
      case Bad(msg) => println("Error: " + msg)
    }

    val result = for {
      a <- makeInt("1")
      b <- makeInt("10")
    } yield a + b
    println(result)
  }
}