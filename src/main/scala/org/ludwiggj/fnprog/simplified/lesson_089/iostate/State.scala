package org.ludwiggj.fnprog.simplified.lesson_089.iostate

case class State[S, A](run: S => (S, A)) {
  def flatMap[B](g: A => State[S, B]): State[S, B] = State { (s0: S) =>
    val (s1: S, a: A) = this.run(s0)
    val newState: (S, B) = g(a).run(s1)
    newState
  }

  def map[B](f: A => B): State[S, B] = State { (s0: S) =>
    val (s1: S, a: A) = this.run(s0)
    (s1, f(a))
  }
}

object State {
  def lift[S, A](v: A): State[S, A] = State(run = s => (s, v))
}