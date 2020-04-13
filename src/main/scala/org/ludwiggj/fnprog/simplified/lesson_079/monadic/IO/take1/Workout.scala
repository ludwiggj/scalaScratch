package org.ludwiggj.fnprog.simplified.lesson_079.monadic.IO.take1

object Workout {
  def println(msg: String): () => Unit =
    () => Predef.println(msg)

  def main(args: Array[String]): Unit = {
    val thunk = this.println("hello!")
    Predef.println("sailor!")
    thunk.apply()
  }
}