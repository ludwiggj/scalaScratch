package org.ludwiggj.fnprog.simplified.lesson_55.curlybrace

object AnotherFIP {

  def s2iAsFunction(): Unit = {
    def s2i(s: String)(f: String => Int) = f(s)

    val stringToInt = s2i("Hello") { s: String =>
      s.length
    }
    println(stringToInt)
  }

  def s2iAsCaseClass(): Unit = {
    case class s2i(s: String)(f: String => Int) {
      // NOTE: f is not automatically exposed outside of the class, so we'll have to define a function to call
      def fun = f(s)
    }

    val stringToInt = s2i("Hello") { s: String =>
      s.length
    }
    println(stringToInt.fun)
  }

  def main(args: Array[String]): Unit = {
    s2iAsFunction()
    s2iAsCaseClass()
  }
}
