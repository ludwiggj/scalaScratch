package org.ludwiggj.fnprog.simplified.lesson_064.bind

object BindWorkout {
  def main(args: Array[String]): Unit = {
//    def f(a: Int): (Int, String) = ???
//    def g(a: Int): (Int, String) = ???
//    def h(a: Int): (Int, String) = ???

    def f(a: Int): (Int, String) = {
      val result = a * 2
      (result, s"f result: $result")
    }
    def g(a: Int): (Int, String) = {
      val result = a * 3
      (result, s"g result: $result")
    }
    def h(a: Int): (Int, String) = {
      val result = a * 4
      (result, s"h result: $result")
    }

    def bind(f: Int => (Int, String), previousRes: (Int, String)): (Int, String) = {
      val (intRes, stringRes) = f(previousRes._1)
      (intRes, s"${previousRes._2}, $stringRes")
    }

    val fResult: (Int, String) = f(100)
    val gResult: (Int, String) = bind(g, fResult)
    val hResult: (Int, String) = bind(h, gResult)

    println(s"fResult: ${fResult}")
    println(s"gResult: ${gResult}")
    println(s"hResult: ${hResult}")
  }
}
