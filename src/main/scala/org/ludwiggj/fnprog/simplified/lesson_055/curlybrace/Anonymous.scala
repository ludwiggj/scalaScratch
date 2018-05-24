package org.ludwiggj.fnprog.simplified.lesson_55.curlybrace

object Anonymous {

  trait Person {
    def name: String

    def age: Int

    override def toString = s"name: $name, age: $age"
  }

  def timer[A](blockOfCode: => A) = {
    val startTime = System.nanoTime
    val result = blockOfCode
    val stopTime = System.nanoTime
    val delta = stopTime - startTime
    (result, delta/1000000d)
  }

  def main(args: Array[String]): Unit = {
    val mary = new Person {
      val name = "mary"
      val age = 22
    }

    println(mary)

    // If Person was a case class...
    case class Peep(name: String, age: Int)

    // NOPE, new doesn't go with case class

    //    val jose1 = new Peep {
    //      val name = "jose1"
    //      val age = 29
    //    }

    // NOPE, case class must pass at least one value into constructor

    //    val jose2 = Peep {
    //      val name = "jose2"
    //      val age = 81
    //    }

    // Case class
    val jose3 = Peep(name = "jose3", age = 41)

    // If Person was a straight class
    class Pers {
      val name: String = ""
      def age: Int = 0
    }

    // If Person is a class with name and age fields, those fields
    // would require an override qualifier when mary is created
    val jen = new Pers {
      override val name = "jen"
      override val age = 22
    }

    // This would work i.e. class does not have name and age fields
    class Citizen

    val burt = new Citizen {
      val name = "burt"
      val age = 25
    }

    val (result, time) = timer {
      Thread.sleep(1000)
      42
    }

    println(s"Result $result, time $time")
  }
}