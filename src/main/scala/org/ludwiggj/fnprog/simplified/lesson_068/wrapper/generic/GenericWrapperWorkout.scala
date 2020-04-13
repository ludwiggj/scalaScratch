package org.ludwiggj.fnprog.simplified.lesson_068.wrapper.generic

class GenericWrapper[A] private(value: A) {
  def map[B](f: A => B): GenericWrapper[B] = GenericWrapper(f(value))

  def flatMap[B](f: A => GenericWrapper[B]): GenericWrapper[B] = f(value)

  override def toString: String = s"Wrapper($value)"
}

object GenericWrapper {
  def apply[A](value: A) = new GenericWrapper(value)
}

object WorkIt {
  def main(args: Array[String]): Unit = {
    println(for {i <- GenericWrapper(1)} yield i * 4)

    val result: GenericWrapper[Int] = for {
      a <- GenericWrapper(10)
      b <- GenericWrapper(20)
      c <- GenericWrapper(30)
    } yield a + b + c
    println(result)

    val stringResult: GenericWrapper[String] = for {
      a <- GenericWrapper("aa")
      b <- GenericWrapper("bb")
      c <- GenericWrapper("cc")
    } yield a + b + c
    println(stringResult)
  }
}