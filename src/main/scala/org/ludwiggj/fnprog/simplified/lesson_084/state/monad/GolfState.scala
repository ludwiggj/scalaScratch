package org.ludwiggj.fnprog.simplified.lesson_084.state.monad

case class GolfState(distance: Int) {
  def flatMap(f: Int => GolfState): GolfState = {
    f(distance)
  }
  def map(f: Int => Int): GolfState = GolfState(f(distance))
}