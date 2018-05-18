package org.ludwiggj.monadic.IO.decomposition

object OptionDesugared {

  def main(args: Array[String]): Unit = {
    // How Options in for-expressions convert to map and flatMap
    val x1 = for {
      i <- Option(1)
      j <- Option(2)
      k <- Option(3)
    } yield i + j + k

    println(s"1 + 2 + 3 = $x1")

    val x2: Option[Int] = Option(1).flatMap { i =>
      Option(2).flatMap { j =>
        Option(3).map { k =>
          i + j + k
        }
      }
    }

    println(s"1 + 2 + 3 = $x2")
  }
}