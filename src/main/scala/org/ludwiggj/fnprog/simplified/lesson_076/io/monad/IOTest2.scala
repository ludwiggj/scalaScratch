package org.ludwiggj.fnprog.simplified.lesson_76.io.monad

object IOTest2 extends App {
  def eagerIO(): Unit = {
    import eager._

    def forExpression: IOEager[Unit] = for {
      _ <- putStrLn("First name?")
      firstName <- getLine
      _ <- putStrLn(s"Last name?")
      lastName <- getLine
      fNameUC = firstName.toUpperCase
      lNameUC = lastName.toUpperCase
      _ <- putStrLn(s"First: $fNameUC, Last: $lNameUC")
    } yield ()

    // run the block of code whenever you want to ...
    forExpression
    forExpression
  }

  def lazyIO(): Unit = {
    import lazee._

    def forExpression: IOLazy[Unit] = for {
      _ <- putStrLn("1st name?")
      firstName <- getLine
      _ <- putStrLn(s"nth name?")
      lastName <- getLine
      fNameUC = firstName.toUpperCase
      lNameUC = lastName.toUpperCase
      _ <- putStrLn(s"First: $fNameUC, Last: $lNameUC")
    } yield ()

    // run the block of code whenever you want to ...
    forExpression.run
    forExpression.run
  }

  eagerIO()
  lazyIO()
}