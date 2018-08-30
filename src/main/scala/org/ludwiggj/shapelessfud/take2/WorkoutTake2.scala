package org.ludwiggj.shapelessfud.take2

import org.ludwiggj.shapelessfud.Show
import shapeless._

object WorkoutTake2 {
  def main(args: Array[String]): Unit = {

    implicit val showHNil: Show[HNil] = _ => ""

    implicit def showCons[H: Show, T <: HList: Show]: Show[H :: T] =
      cons => Show[H].show(cons.head) + ", " + Show[T].show(cons.tail)

    implicit def showProduct[S, T <: HList](implicit gen: Generic.Aux[S, T],
                                            show: Show[T]): Show[S] =
      s => {
        val hlist: T = gen.to(s) // our concrete S to hlist T translation!
        val result = show.show(hlist)
        result.substring(0, result.length - 2) // drop ", "
      }

    val someStuff = Stuff("stuff", 2.4, Map("a" -> "b"))
    println(Show[Stuff].show(someStuff))
  }
}