package org.ludwiggj.fnprog.simplified.lesson_079.cats

import cats.effect.IO

object IOMonadAlmostHelloWorld extends App {
  val hello: IO[Unit] = IO { println("Hello, world") }
}