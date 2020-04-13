package org.ludwiggj.fnprog.simplified.lesson_076.io.monad

object IOWorkout {
  def inputNameAndPrintItEager(): Unit = {
    import eager._

    for {
      _ <- putStrLn("First name?")
      firstName <- getLine
      _ <- putStrLn(s"Last name?")
      lastName <- getLine
      _ <- putStrLn(s"First: $firstName, Last: $lastName")
    } yield ()
  }

  def inputNameAndPrintItCapitalisedEager(): IOEager[Unit] = {
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
  }

  def inputNameAndPrintItCapitalisedEagerDesugared(): IOEager[Unit] = {
    import eager._

    def forExpression: IOEager[Unit] = eager.putStrLn("First name?")
      .flatMap(_ =>
        getLine
          .flatMap(firstName =>
            eager.putStrLn("Last name?")
              .flatMap(_ =>
                getLine
                  .flatMap(lastName => {
                    val fNameUC: String = firstName.toUpperCase
                    val lNameUC: String = lastName.toUpperCase

                    eager.putStrLn(s"First: $fNameUC, Last: $lNameUC")
                      .map(_ => ())
                  })
              )
          )
      )

    // run the block of code whenever you want to ...
    forExpression
  }

  def inputNameAndPrintItLazy(): IOLazy[Unit] = {
    import lazee._

    for {
      _ <- putStrLn("1st name?")
      firstName <- getLine
      _ <- putStrLn(s"nth name?")
      lastName <- getLine
      _ <- putStrLn(s"First: $firstName, Last: $lastName")
    } yield ()
  }

  def inputNameAndPrintItCapitalisedLazy(): Unit = {
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
  }

  def inputNameAndPrintItCapitalisedLazyDesugared(): Unit = {
    import lazee._

    def forExpression: IOLazy[Unit] = lazee.putStrLn("1st name?")
      .flatMap(_ =>
        getLine
          .flatMap(firstName =>
            lazee.putStrLn("nth name?")
              .flatMap(_ =>
                getLine
                  .flatMap(lastName => {
                    val fNameUC: String = firstName.toUpperCase
                    val lNameUC: String = lastName.toUpperCase

                    lazee.putStrLn(s"First: $fNameUC, Last: $lNameUC")
                      .map(_ => ())
                  })
              )
          )
      )

    // run the block of code whenever you want to ...
    forExpression.run
  }

  def main(args: Array[String]): Unit = {
    //    inputNameAndPrintItEager()

    //    inputNameAndPrintItCapitalisedEager()

    //    inputNameAndPrintItCapitalisedEagerDesugared()

    // Doesn't execute!
    //    inputNameAndPrintItLazy()
    // This does
    //    inputNameAndPrintItLazy().run

    //    inputNameAndPrintItCapitalisedLazy()

    inputNameAndPrintItCapitalisedLazyDesugared()
  }
}