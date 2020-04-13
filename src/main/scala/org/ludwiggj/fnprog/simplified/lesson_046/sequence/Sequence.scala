package org.ludwiggj.fnprog.simplified.lesson_046.sequence

case class Sequence[A](initialElems: A*) {
  // this is a book, don't do this at home
  private val elems = scala.collection.mutable.ArrayBuffer[A]()

  // initialize
  elems ++= initialElems
}

object Main {
  def main(args: Array[String]): Unit = {
    val ints = Sequence(1, 2, 3)
    val strings = Sequence("a", "b", "c")

    println(ints)
    println(strings)
  }
}
