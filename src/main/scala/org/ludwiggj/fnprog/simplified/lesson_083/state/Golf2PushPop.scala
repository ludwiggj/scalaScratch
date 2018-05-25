package org.ludwiggj.fnprog.simplified.lesson_083.state

object Golf2PushPop {
  def push[A](xs: List[A], a: A): List[A] = a :: xs

  def pop[A](xs: List[A]): (A, List[A]) = (xs.head, xs.tail)

  def main(args: Array[String]): Unit = {
    val state0: List[Int] = Nil
    val state1 = push(state0, 20)
    val state2 = push(state1, 10)
    val state3 = push(state2, 0)

    println(state3)

    val (swing3, s2) = pop(state3)
    println(s"swing3 = $swing3")
    println(s"state2 = $s2")
  }
}
