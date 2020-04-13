package org.ludwiggj.fnprog.simplified.lesson_084.state.monad

object Golf {

  def play(): Unit = {
    val res = for {
      a <- GolfState(20)
      b <- GolfState(a + 15) // manually carry over 'a'
      c <- GolfState(b + 0) // manually carry over 'b'
    } yield c

    println(s"Game result: $res")
  }

  def main(args: Array[String]): Unit = {
    play()
  }
}