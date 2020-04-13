package org.ludwiggj.fnprog.simplified.lesson_047.sequence

case class Sequence[A](initialElems: A*) {
  // this is a book, don't do this at home
  private val elems = scala.collection.mutable.ArrayBuffer[A]()

  // initialize
  elems ++= initialElems

  def foreach(block: A => Unit): Unit = {
    elems.foreach(block)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val strings = Sequence("a", "b", "c")
    
    for (s <- strings) {
      println(s)
    }
  }
}
