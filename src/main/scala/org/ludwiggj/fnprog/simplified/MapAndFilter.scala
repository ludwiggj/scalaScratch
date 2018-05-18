package org.ludwiggj.fnprog.simplified

object MapAndFilter {
  def main(args: Array[String]): Unit = {
    def map[A](f: Int => A, list: List[Int]): List[A] = {
      for {
        x <- list
      } yield f(x) //<-- apply 'f' to each element 'x'
    }

    def filter(f: Int => Boolean, list: List[Int]): List[Int] = {
      for {
        x <- list if f(x)
      } yield x
    }

    println(map(_ * 2, List(1, 2, 3)))
    println(filter(_ % 2 == 0, List(1, 2, 3)))
    println(filter(_ > 1, List(1, 2, 3)))
  }
}
