package org.ludwiggj.fnprog.simplified.lesson_083.state.monad.take1

object StateTester {
  def main(args: Array[String]): Unit = {
    val res = for {
      a <- GolfState(20)
      b <- GolfState(a + 15)
      c <- GolfState(b + 0)
    } yield c

    println(res)
  }
}
