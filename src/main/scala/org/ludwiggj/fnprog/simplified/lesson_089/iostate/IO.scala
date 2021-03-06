package org.ludwiggj.fnprog.simplified.lesson_089.iostate

class IO[A] private(constructorCodeBlock: => A) {
  def run: A = constructorCodeBlock

  def flatMap[B](f: A => IO[B]): IO[B] = f(run)

  def map[B](f: A => B): IO[B] = IO(f(run))
}

object IO {
  def apply[A](a: => A): IO[A] = new IO(a)
}