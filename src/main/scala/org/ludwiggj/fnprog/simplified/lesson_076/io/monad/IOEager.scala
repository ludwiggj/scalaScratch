package org.ludwiggj.fnprog.simplified.lesson_076.io.monad

class IOEager[A] private(constructorCodeBlock: => A) {
  // run is just a nice handle to the "by-name" code block used to instantiate the class (constructorCodeBlock)
  def run: A = {
    println("IOEager run")
    constructorCodeBlock
  }

  // (1) flatMap

  // NOTE: This doesn't work eagerly. It's actually the implementation for the lazy version!
  //       This is because the whole of f(this.run).run is treated as the pass-by-name parameter

  //  def flatMap[B](f: A => IOEager[B]): IOEager[B] = {
  //    IOEager(f(this.run).run)
  //  }

  // This does work eagerly
  def flatMap[B](f: A => IOEager[B]): IOEager[B] = {
    println("IOEager flatMap")
    f(this.run)
  }

  // (2) Map
  def map[B](f: A => B): IOEager[B] = {
    println("IOEager map")
    val b = f(this.run)
    IOEager(b)
  }

  // Alternative versions:

  // def map[B](f: A => B): IOEager[B] = {
  //   flatMap(a => IOEager(f(a)))
  // }

  //  def map[B](f: A => B): IOEager[B] = {
  //    IOEager(IOEager(f(this.run)).run)
  //  }
}

object IOEager {
  def apply[A](a: => A): IOEager[A] = new IOEager(a)
}