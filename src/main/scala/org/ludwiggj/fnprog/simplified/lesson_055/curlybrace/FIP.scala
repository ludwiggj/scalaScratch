package org.ludwiggj.fnprog.simplified.lesson_55.curlybrace

object FIP {

  def simpleExample(): Unit = {
    case class StringToInt(run: String => Int)

    val stringToInt = StringToInt { s: String =>
      s.length
    }
    println(stringToInt.run("bananas"))

    def len(s: String): Int = s.length

    val stringToInt2 = StringToInt(len)
    println(stringToInt2.run("fruit"))

    val lenz: String => Int = _.length
    val stringToInt3 = StringToInt(lenz)
    println(stringToInt3.run("pineapple"))
  }

  def advancedExample(): Unit = {
    case class Transform2ParamsTo1Param[A, B](fun: (A, A) => B)

    val x = Transform2ParamsTo1Param { (a: String, b: String) =>
      a.length + b.length
    }

    println(x.fun("foo", "bar"))

    val y = Transform2ParamsTo1Param { (a: Int, b: Int) =>
      a + b
    }

    println(y.fun(1, 2))
  }

  def main(args: Array[String]): Unit = {
    simpleExample()
    advancedExample()
  }
}
