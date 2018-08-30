package org.ludwiggj.cake.example0

trait Thing[-A, +B] {
  type X

  def source(a: A): X

  def sink(x: X): B

  def apply(x: X): X
}

object Main extends App {
  def bippy(t: Thing[Int, String]): String = {
    val state: t.X = t source 42
    val state2: t.X = t(state)
    t sink state
  }


  val thingy = new Thing[Int, String] {
    override type X = String

    override def source(a: Int): String = a.toString

    override def sink(x: String): String = x

    override def apply(x: String): String = x
  }

  println(bippy(thingy))
}