package org.ludwiggj.fnprog.simplified.lesson_085.state.monad

case class State[S, A](run: S => (S, A)) {
  def flatMap[B](g: A => State[S, B]): State[S, B] = State { s0: S =>
    val (s1: S, a: A) = this.run(s0)
    val newState: (S, B) = g(a).run(s1)
    newState
  }

  def map[B](f: A => B): State[S, B] = flatMap(a => State.lift(f(a)))

  // Alternative
//  def map[B](f: A => B): State[S, B] = State { (s0: S) =>
//    val (s1: S, a: A) = this.run(s0)
//    //        val b: B = f(a)
//    //        val st1: State[S, B] = State(run = (s: S) => (s, b))
//    //        val res: (S, B) = st1.run(s1)
//    //        res

    //     Simpler than the above
//    (s1, f(a))
//  }
}

object State {
  def lift[S, A](v: A): State[S, A] = State(run = s => (s, v))
}