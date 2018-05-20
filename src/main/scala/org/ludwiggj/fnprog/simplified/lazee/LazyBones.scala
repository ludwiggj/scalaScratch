package org.ludwiggj.fnprog.simplified.lazee

object LazyBones {
  def main(args: Array[String]): Unit = {
    def lessThan30(i: Int): Boolean = {
      println(s"\n$i less than 30?")
      i < 30
    }

    def moreThan20(i: Int): Boolean = {
      println(s"$i more than 20?")
      i > 20
    }

    def lazyRun(): Unit = {
      println("Lazy run...")
      val a = List(1, 25, 40, 5, 23)
      val q0 = a.withFilter(lessThan30)
      val q1 = q0.withFilter(moreThan20)
      for (r <- q1) println(s"$r")
    }

    def strictRun(): Unit = {
      println("Strict run...")
      val a = List(1, 25, 40, 5, 23)
      val q0 = a.filter(lessThan30)
      val q1 = q0.filter(moreThan20)
      for (r <- q1) println(s"$r")
    }

    // https://docs.scala-lang.org/tutorials/FAQ/yield.html
    def anotherExampleLazy(): Unit = {
      println("Another lazy example")
      var found = false
      Stream.range(1,10).filter(_ % 2 == 1 && !found).foreach(x => if (x == 5) found = true else println(x))
    }

    def anotherExampleStrictFilter(): Unit = {
      println("Another strict example")
      var found = false
      List.range(1,10).filter(_ % 2 == 1 && !found).foreach(x => if (x == 5) found = true else println(x))
    }

    def anotherExampleLazyWithFilter(): Unit = {
      println("Another lazy example")
      var found = false
      List.range(1,10).withFilter(_ % 2 == 1 && !found).foreach(x => if (x == 5) found = true else println(x))
    }

    lazyRun()
    strictRun()

    anotherExampleLazy()
    anotherExampleStrictFilter()
    anotherExampleLazyWithFilter()
  }
}
