package org.ludwiggj.fnprog.simplified.lesson_089.iostate

import org.ludwiggj.fnprog.simplified.lesson_085.state.monad.State

object TryStateAndIoTogether extends App {
  // State code
  type Stack = List[String]

  def push(x: String): State[Stack, Unit] = State[Stack, Unit] {
    xs => (x :: xs, ())
  }

  // IO functions
  def getLine: IO[String] = IO(scala.io.StdIn.readLine())

  def putStrLn(s: String): IO[Unit] = IO(println(s))

  // main loop: Prompt a user for some input,
  // then push that input onto a stack

  // TODO - WIP

//  val res =
//    putStrLn("Type anything:")
//      .flatMap({ case _ => getLine
//        .flatMap(input => push(input)
//          .flatMap { case _ => putStrLn(s"Input: $input")
//            .map { case _ => () }})})

  /*
  val res = for {
    _ <- putStrLn("Type anything:") //IO
    input <- getLine //IO
    _ <- push(input) //State
    _ <- putStrLn(s"Input: $input") //IO
  } yield ()
   */
}
