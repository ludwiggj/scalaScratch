package org.ludwiggj.fnprog.simplified.bind.debuggable.generic

case class GenericDebuggableLog[A](value: A, log: List[String]) {
  def map[B](f: A => B): GenericDebuggableLog[B] = GenericDebuggableLog(f(value), this.log)

  def flatMap[B](f: A => GenericDebuggableLog[B]): GenericDebuggableLog[B] = {
    val next = f(value)
    GenericDebuggableLog(next.value, this.log ++ next.log)
  }
}

object GenericDebuggableExample2 {
  def main(args: Array[String]): Unit = {

    def f(a: Int): GenericDebuggableLog[Int] = {
      val result = a * 2
      GenericDebuggableLog(result, List(s"f rez: $result."))
    }

    def g(a: Int): GenericDebuggableLog[Int] = {
      val result = a * 3
      GenericDebuggableLog(result, List(s"g rez: $result."))
    }

    def h(a: Int): GenericDebuggableLog[Int] = {
      val result = a * 4
      GenericDebuggableLog(result, List(s"h rez: $result."))
    }

    val finalResult = for {
      fResult <- f(100)
      gResult <- g(fResult)
      hResult <- h(gResult)
    } yield hResult

    // added a few "\n" to make the output easier to read
    println(s"value: ${finalResult.value}\n")
    println(s"message: \n${finalResult.log.mkString("\n")}")
  }
}