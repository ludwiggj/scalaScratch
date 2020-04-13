package org.ludwiggj.lens

sealed trait Pet

case class Dog(name: String) extends Pet

case class Cat(name: String) extends Pet

case object NoPetYet extends Pet

case class Prism[S, A](_getOption: S => Option[A])(_reverseGet: A => S) {
  def getOption(s: S): Option[A] = _getOption(s)

  def reverseGet(a: A): S = _reverseGet(a)
}

object PrismWorkout {
  def main(args: Array[String]): Unit = {

    val petPrism: Prism[Pet, String] = Prism[Pet, String] {
      case Dog(n) => Some(n)
      case _ => None
    }(name => Dog(name))

    println(petPrism.getOption(Dog("Santa's Little Helper")))
    println(petPrism.reverseGet("Santa's Little Helper"))
  }
}