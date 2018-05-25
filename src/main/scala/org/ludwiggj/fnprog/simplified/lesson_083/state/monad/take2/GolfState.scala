package org.ludwiggj.fnprog.simplified.lesson_083.state.monad.take2

case class GolfState(distances: List[Int]) {
  def pos: Int = distances.sum
}

object Game {

  def swing(distance: Int): State[GolfState, Int] = State { (previousState : GolfState) =>
    val newState = GolfState(distance +: previousState.distances)
    (newState, newState.pos)
  }

  def executeSwing(initialPos: Int,
                   theShot: State[GolfState, Int],
                   currentState: GolfState = GolfState(Nil)): (GolfState, Int) = {
    println(s"Initial position $initialPos")
    val (gs, newPos): (GolfState, Int) = theShot.run(currentState)
    println(s"Shot! New position: $newPos")
    (gs, newPos)
  }

  def getAndExecuteSwing(distance: Int, golfState: GolfState = GolfState(Nil)): GolfState = {
    val theSwing: State[GolfState, Int] = swing(distance)
    println(s"About to shoot $distance...")
    val (gs, pos) = executeSwing(golfState.pos, theSwing, golfState)
    println(s"shot! distance: $gs, position: $pos\n\n")
    gs
  }

  private def threeShotGame: Unit = {
    val firstSwing: State[GolfState, Int] = swing(20)
    println("About to shoot 20...")
    val (gs, pos) = executeSwing(0, firstSwing)
    println(s"gs: $gs, pos: $pos\n\n")

    val secondSwing: State[GolfState, Int] = swing(47)
    println("About to shoot 47...")
    val (gs2, pos2) = executeSwing(pos, secondSwing, gs)
    println(s"gs: $gs2, pos: $pos2\n\n")

    val thirdSwing: State[GolfState, Int] = swing(31)
    println("About to shoot 31...")
    val (gs3, pos3) = executeSwing(pos2, thirdSwing, gs2)
    println(s"gs: $gs3, pos: $pos3\n\n")
  }

  private def fourShotGame: Unit = {
    val gs1 = getAndExecuteSwing(58)
    val gs2 = getAndExecuteSwing(114, gs1)
    val gs3 = getAndExecuteSwing(71, gs2)
    val gs4 = getAndExecuteSwing(13, gs3)
  }

  private def threeShotGameWithMap: Unit = {
    val firstSwing: State[GolfState, Int] = swing(20).map(_ * 2)
    println("About to shoot 20.map (doubled)...")
    val (gs, pos) = executeSwing(0, firstSwing)
    println(s"gs: $gs, pos: $pos\n\n")

    val secondSwing: State[GolfState, Int] = swing(47).map(_ * 3)
    println("About to shoot 47.map (trebled)...")
    val (gs2, pos2) = executeSwing(pos, secondSwing, gs)
    println(s"gs: $gs2, pos: $pos2\n\n")

    val thirdSwing: State[GolfState, Int] = swing(13).map(_ * 10)
    println("About to shoot 13 (* 10)...")
    val (gs3, pos3) = executeSwing(pos2, thirdSwing, gs2)
    println(s"gs: $gs3, pos: $pos3\n\n")
  }

  private def twoShotGameFlatmapped: Unit = {
    val firstSwing: State[GolfState, Int] = swing(84)
    val secondSwing: State[GolfState, Int] = swing(16)

    val firstSecondSwing = firstSwing.flatMap(a => {println(s"Received $a"); secondSwing})
    val (gs1, pos1) = executeSwing(0, firstSecondSwing)
    println(s"gs: $gs1, pos: $pos1\n\n")

    val secondFirstSwing = secondSwing.flatMap(a => {println(s"Received $a"); firstSwing})
    val (gs2, pos2) = executeSwing(0, secondFirstSwing)
    println(s"gs: $gs2, pos: $pos2\n\n")

    val doubleFirstSwing = firstSwing.flatMap(shot => swing(shot))
    val (gs3, pos3) = executeSwing(0, doubleFirstSwing)
    println(s"gs: $gs3, pos: $pos3\n\n")

    val doubleSecondSwing = secondSwing.flatMap(shot => swing(shot))
    val (gs4, pos4) = executeSwing(0, doubleSecondSwing)
    println(s"gs: $gs4, pos: $pos4\n\n")
  }

  def forSwings: Unit = {
    val stateWithNewDistance: State[GolfState, Int] = for {
      _ <- swing(20)
      _ <- swing(15)
      totalDistance <- swing(0)
    } yield totalDistance

    println(s"Total distance = ${stateWithNewDistance.run(GolfState(Nil))}")
  }

  def main(args: Array[String]): Unit = {
    //    threeShotGame
    //    fourShotGame
    //    threeShotGameWithMap
    //    twoShotGameFlatmapped

    forSwings
  }
}