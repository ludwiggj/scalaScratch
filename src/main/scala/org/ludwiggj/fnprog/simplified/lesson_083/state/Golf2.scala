package org.ludwiggj.fnprog.simplified.lesson_083.state

object Golf2 {
  case class GolfState(strokes: List[Int])

  def nextStroke(previousState: GolfState, nextDistance: Int): GolfState = {
    GolfState(nextDistance +: previousState.strokes)
  }

  def main(args: Array[String]): Unit = {
    val state0 = GolfState(Nil)
    val state1 = nextStroke(state0, 20)
    val state2 = nextStroke(state1, 15)
    val state3 = nextStroke(state2, 0)

    println(state3)
  }
}
