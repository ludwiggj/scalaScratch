package org.ludwiggj.fnprog.simplified.lesson_76.io.monad

class IOEager[A] private(constructorCodeBlock: => A) {
  def run: A = constructorCodeBlock

  // def flatMapOrig[B](f: A => IO[B]): IO[B] = IO(f(run).run)

  // This is just an expanded version of flatMapOrig
  def flatMap[B](customAlgorithm: A => IOEager[B]): IOEager[B] = {
    val result1: IOEager[B] = customAlgorithm(run)
    val result2: B = result1.run
    IOEager(result2)
  }

  // This was original version

  // def map[B](f: A => B): IO[B] = flatMap(a => IO(f(a)))

  // Which expands to...
  def map[B](f: A => B): IOEager[B] = IOEager(IOEager(f(run)).run)

  // Which is eager as it makes an extra call to run to kick it off
}

object IOEager {
  def apply[A](a: => A): IOEager[A] = new IOEager(a)
}