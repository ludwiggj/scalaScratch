package org.ludwiggj.lens

sealed trait Box

case class Present(quantity: Int) extends Box

case object NoPresent extends Box

case class Optional[S, A](_getOption: S => Option[A])(_set: A => S => S) {
  def getOption(s: S): Option[A] = _getOption(s)

  def set(a: A): S => S = _set(a)
}

object OptionalWorkout {
  def main(args: Array[String]): Unit = {

    val maybePresents = Optional[Box, Int] {
      case Present(q) => Some(q)
      case _ => None
    } { numberOfPresents => {
      case p: Present => p.copy(quantity = numberOfPresents)
      case box => box
    }
    }

    println(maybePresents.getOption(Present(3)))
    println(maybePresents.getOption(NoPresent))

    println(maybePresents.set(7)(Present(8)))
    println(maybePresents.set(7)(NoPresent))
  }
}