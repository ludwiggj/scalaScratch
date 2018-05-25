package org.ludwiggj.fnprog.simplified.lesson_083.state

object Golf {
  case class GolfState(distance: Int)

  def nextStroke(previousState: GolfState, nextDistance: Int): GolfState = {
    GolfState(previousState.distance + nextDistance)
  }

  def main(args: Array[String]): Unit = {
    val state1 = GolfState(20)
    val state2 = nextStroke(state1, 15)
    val state3 = nextStroke(state2, 0)

    println(state3)
  }
}
