package org.ludwiggj.monadic.IO.take2

object Example {
  def ioComprehension: Pure.IO[Int] = {
    for {
      _ <- Pure.println("Starting work now.")
      // Do some pure work
      x = 1 + 2 + 3
      _ <- Pure.println("All done. Home time.")
    } yield x
  }

  import org.ludwiggj.monadic.IO.take2.Pure.IO

  def ioComprehensionDesugared: Pure.IO[Int] = {
    Pure.println("Starting work now.").flatMap { _ =>
      IO.point(1 + 2 + 3).flatMap { i =>
        Pure.println("All done. Home time.").map { _ =>
          i
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val io = ioComprehension
    println("About to start...")
    def result: Int = io.run
    println(s"Result is $result")

    val ioDesugared = ioComprehensionDesugared
    println("About to start again...")
    def resultDesugared: Int = ioDesugared.run
    println(s"Desugared result is $resultDesugared")
  }
}