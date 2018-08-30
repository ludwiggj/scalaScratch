package org.ludwiggj.shapelessfud.take1

import org.ludwiggj.shapelessfud.Show

object WorkoutTake1 {

  def main(args: Array[String]): Unit = {
    val hlist: Map[String, String] :: HNil = Map.empty[String, String] :: HNil

    val anotherHList: Double :: Map[String, String] :: HNil = 0.0 :: hlist
    val yetAnotherHList: String :: Double :: Map[String, String] :: HNil = "test" :: anotherHList

    implicit val showHNil: Show[HNil] = _ => ""

    implicit def showCons[H: Show, T <: HList : Show]: Show[H :: T] =
      cons => Show[H].show(cons.head) + ", " + Show[T].show(cons.tail)

    // "test, 0.0, {}, "
    println(
      Show[String :: Double :: Map[String, String] :: HNil].show(yetAnotherHList)
    )
  }
}