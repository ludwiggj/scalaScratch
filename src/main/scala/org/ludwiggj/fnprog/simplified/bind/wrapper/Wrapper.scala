package org.ludwiggj.fnprog.simplified.bind.wrapper

class Wrapper[Int](value: Int) {
  def map(f: Int => Int): Wrapper[Int] = new Wrapper(f(value))

  def flatMap(f: Int => Wrapper[Int]): Wrapper[Int] = f(value)

  override def toString: String = s"Wrapper($value)"
}

object Wrapper {
  def main(args: Array[String]): Unit = {
    println(for {i <- new Wrapper(1)} yield i * 2)

    val result: Wrapper[Int] = for {
      a <- new Wrapper(1)
      b <- new Wrapper(2)
      c <- new Wrapper(3)
    } yield a + b + c

    println(result)
  }
}