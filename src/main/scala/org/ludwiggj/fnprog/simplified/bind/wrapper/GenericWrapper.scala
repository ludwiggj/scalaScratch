package org.ludwiggj.fnprog.simplified.bind.wrapper

class GenericWrapper[A](value: A) {
  def map[B](f: A => B): GenericWrapper[B] = new GenericWrapper(f(value))

  def flatMap[B](f: A => GenericWrapper[B]): GenericWrapper[B] = f(value)

  override def toString: String = s"Wrapper($value)"
}

object GenericWrapper {
  def main(args: Array[String]): Unit = {
    println(for {i <- new GenericWrapper(1)} yield i * 2)

    val result: GenericWrapper[Int] = for {
      a <- new GenericWrapper(1)
      b <- new GenericWrapper(2)
      c <- new GenericWrapper(3)
    } yield a + b + c
    println(result)

    val stringResult: GenericWrapper[String] = for {
      a <- new GenericWrapper("a")
      b <- new GenericWrapper("b")
      c <- new GenericWrapper("c")
    } yield a + b + c
    println(stringResult)
  }
}