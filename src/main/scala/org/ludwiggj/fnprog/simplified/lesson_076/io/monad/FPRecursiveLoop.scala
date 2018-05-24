package org.ludwiggj.fnprog.simplified.lesson_76.io.monad

object FPRecursiveLoop extends App {

  def lazyLoop(): Unit = {
    import lazee._
    def loop: IOLazy[Unit] = for {
      _ <- putStrLn("Type something: ")
      input <- getLine
      _ <- putStrLn(s"You said '$input'.")
      _ <- if (input == "quit") IOLazy(Unit) else loop //RECURSE
    } yield ()

    loop.run
  }

  def eagerLoop(): Unit = {
    import eager._
    def loop: IOEager[Unit] = for {
      _ <- putStrLn("Oy! Type something: ")
      input <- getLine
      _ <- putStrLn(s"You spit '$input'.")
      _ <- if (input == "quit") IOEager(Unit) else loop //RECURSE
    } yield ()

    loop
  }

  def anotherLoop(): Unit = {
    import eager._
    def loop: IOEager[Unit] = return eager.putStrLn("Oy! Type something: ")
      .flatMap({ case _ => getLine
        .flatMap((input: String) => eager.putStrLn(s"You spit '$input'.")
          .flatMap { case _ => (if (input == "quit") IOEager(Unit) else loop)
            .map { case _ => () }
          }
        )
      })

    loop
  }

  //  lazyLoop()
  //  eagerLoop()
  anotherLoop()
}