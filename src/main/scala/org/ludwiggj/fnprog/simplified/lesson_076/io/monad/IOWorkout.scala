package org.ludwiggj.fnprog.simplified.lesson_76.io.monad

object IOTest1 {
  def eagerIO(): Unit = {
    import eager._

    for {
      _ <- putStrLn("First name?")
      firstName <- getLine
      _ <- putStrLn(s"Last name?")
      lastName <- getLine
      _ <- putStrLn(s"First: $firstName, Last: $lastName")
    } yield ()
  }

  def lazyIO(): Unit = {
    import lazee._

    for {
      _ <- putStrLn("1st name?")
      firstName <- getLine
      _ <- putStrLn(s"nth name?")
      lastName <- getLine
      _ <- putStrLn(s"First: $firstName, Last: $lastName")
    } yield ()
  }

  def main(args: Array[String]): Unit = {
    eagerIO()
    lazyIO()  // Doesn't execute!
  }
}