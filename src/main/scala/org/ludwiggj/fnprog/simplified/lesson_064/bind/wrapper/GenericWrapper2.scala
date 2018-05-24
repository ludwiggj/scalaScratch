package org.ludwiggj.fnprog.simplified.lesson_64_bind.wrapper

class GenericWrapper2[A] private (value: A) {
  def map[B](f: A => B): GenericWrapper2[B] = GenericWrapper2(f(value))

  def flatMap[B](f: A => GenericWrapper2[B]): GenericWrapper2[B] = f(value)

  override def toString: String = s"Wrapper($value)"
}

object GenericWrapper2 {
  def apply[A](value: A) = new GenericWrapper2(value)
}

object WorkIt {
  def main(args: Array[String]): Unit = {
    println(for {i <- GenericWrapper2(1)} yield i * 4)

    val result: GenericWrapper2[Int] = for {
      a <- GenericWrapper2(10)
      b <- GenericWrapper2(20)
      c <- GenericWrapper2(30)
    } yield a + b + c
    println(result)

    val stringResult: GenericWrapper2[String] = for {
      a <- GenericWrapper2("aa")
      b <- GenericWrapper2("bb")
      c <- GenericWrapper2("cc")
    } yield a + b + c
    println(stringResult)
  }
}