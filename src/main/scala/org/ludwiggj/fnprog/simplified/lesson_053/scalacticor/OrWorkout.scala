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

    def makeInt2(s: String): Unit = {
      makeInt(s) match {
        case Good(i) => println("Answer: " + i)
        case Bad(msg) => println("Error: " + msg)
      }
    }

    makeInt2("23")
    makeInt2("wibble")

    val result = for {
      a <- makeInt("1")
      b <- makeInt("10")
    } yield a + b
    println(result)
  }
}