package org.ludwiggj.fnprog.simplified.bind.debuggable

case class Debuggable(value: Int, message: String) {
  def map[B](f: Int => Int): Debuggable = Debuggable(f(value), this.message)

  def flatMap[B](f: Int => Debuggable): Debuggable = {
    val next = f(value)
    Debuggable(next.value, s"$message\n${next.message}")
  }
}

object DebuggableExample {
  def main(args: Array[String]): Unit = {

    def f(a: Int): Debuggable = {
      val result = a * 2
      Debuggable(result, s"f result: $result.")
    }

    def g(a: Int): Debuggable = {
      val result = a * 3
      Debuggable(result, s"g result: $result.")
    }

    def h(a: Int): Debuggable = {
      val result = a * 4
      Debuggable(result, s"h result: $result.")
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