package org.ludwiggj.fnprog.simplified.bind.debuggable.generic

case class GenericDebuggable[A](value: A, message: String) {
  def map[B](f: A => B): GenericDebuggable[B] = GenericDebuggable(f(value), this.message)

  def flatMap[B](f: A => GenericDebuggable[B]): GenericDebuggable[B] = {
    val next = f(value)
    GenericDebuggable(next.value, s"$message\n${next.message}")
  }
}

object GenericDebuggableExample {
  def main(args: Array[String]): Unit = {

    def f(a: Int): GenericDebuggable[Int] = {
      val result = a * 2
      GenericDebuggable(result, s"f result: $result.")
    }

    def g(a: Int): GenericDebuggable[Int] = {
      val result = a * 3
      GenericDebuggable(result, s"g result: $result.")
    }

    def h(a: Int): GenericDebuggable[Int] = {
      val result = a * 4
      GenericDebuggable(result, s"h result: $result.")
    }

    val finalResult = for {
      fResult <- f(100)
      gResult <- g(fResult)
      hResult <- h(gResult)
    } yield hResult

    // added a few "\n" to make the output easier to read
    println(s"value: ${finalResult.value}\n")
    println(s"message: \n${finalResult.message}")
  }
}