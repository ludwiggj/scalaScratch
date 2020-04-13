package org.ludwiggj.fnprog.simplified.lesson_072.bindInFor.generic

case class GenericDebuggable[A](value: A, log: List[String]) {
  def map[B](f: A => B): GenericDebuggable[B] = GenericDebuggable(f(value), this.log)

  def flatMap[B](f: A => GenericDebuggable[B]): GenericDebuggable[B] = {
    val next = f(value)
    GenericDebuggable(next.value, this.log ++ next.log)
  }
}

object GenericDebuggableWorkout {
  def main(args: Array[String]): Unit = {

    def f(a: Int): GenericDebuggable[Int] = {
      val result = a * 2
      GenericDebuggable(result, List(s"f: multiply $a * 2 = $result"))
    }

    def g(a: Int): GenericDebuggable[Int] = {
      val result = a * 3
      GenericDebuggable(result, List(s"g: multiply $a * 3 = $result"))
    }

    def h(a: Int): GenericDebuggable[Int] = {
      val result = a * 4
      GenericDebuggable(result, List(s"h: multiply $a * 4 = $result"))
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