package org.ludwiggj.fnprog.simplified.lesson_083.state

object Golf {

  object Game1 {
    case class GolfState(distance: Int)

    def nextStroke(previousState: GolfState, nextDistance: Int): GolfState = {
      GolfState(previousState.distance + nextDistance)
    }

    def play(): Unit = {
      val state1 = GolfState(20)
      val state2 = nextStroke(state1, 15)
      val state3 = nextStroke(state2, 0)

      println(s"Game 1 result: $state3")
    }
  }

  object Game2 {
    case class GolfState(strokes: List[Int])

    def nextStroke(previousState: GolfState, nextDistance: Int): GolfState = {
      GolfState(nextDistance +: previousState.strokes)
    }

    def play(): Unit = {
      val state0 = GolfState(Nil)
      val state1 = nextStroke(state0, 20)
      val state2 = nextStroke(state1, 15)
      val state3 = nextStroke(state2, 0)

      println(s"Game 2 result: $state3")
    }
  }

  object Game3 {
    def push[A](xs: List[A], a: A): List[A] = a :: xs

    def pop[A](xs: List[A]): (A, List[A]) = (xs.head, xs.tail)

    def play(): Unit = {
      val state0: List[Int] = Nil
      val state1 = push(state0, 20)
      val state2 = push(state1, 10)
      val state3 = push(state2, 0)

      println(s"Game 3 result: $state3")

      val (swing3, s2) = pop(state3)
      println(s"swing3 = $swing3")
      println(s"state2 = $s2")
    }
  }

  def main(args: Array[String]): Unit = {
    Game1.play()
    Game2.play()
    Game3.play()
  }
}
