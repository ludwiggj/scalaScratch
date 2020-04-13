package org.ludwiggj.fnprog.simplified.lesson_067.wrapper.generic

class GenericWrapperWorkout[A](value: A) {
  def map[B](f: A => B): GenericWrapperWorkout[B] = new GenericWrapperWorkout(f(value))

  def flatMap[B](f: A => GenericWrapperWorkout[B]): GenericWrapperWorkout[B] = f(value)

  override def toString: String = s"Wrapper($value)"
}

object GenericWrapperWorkout {
  def main(args: Array[String]): Unit = {
    println(for {i <- new GenericWrapperWorkout(1)} yield i * 2)

    val result: GenericWrapperWorkout[Int] = for {
      a <- new GenericWrapperWorkout(1)
      b <- new GenericWrapperWorkout(2)
      c <- new GenericWrapperWorkout(3)
    } yield a + b + c
    println(result)

    val stringResult: GenericWrapperWorkout[String] = for {
      a <- new GenericWrapperWorkout("a")
      b <- new GenericWrapperWorkout("b")
      c <- new GenericWrapperWorkout("c")
    } yield a + b + c
    println(stringResult)
  }
}