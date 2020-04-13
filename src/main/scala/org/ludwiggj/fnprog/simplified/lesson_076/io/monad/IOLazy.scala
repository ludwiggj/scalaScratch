package org.ludwiggj.fnprog.simplified.lesson_076.io.monad

class IOLazy[A] private(constructorCodeBlock: => A) {
  def run: A = {
    println("IOLazy run")
    constructorCodeBlock
  }

  // This is lazy because the whole of f(this.run).run is treated as the pass-by-name parameter
  def flatMap[B](f: A => IOLazy[B]): IOLazy[B] = {
    println("IOLazy flatMap")
    IOLazy(f(this.run).run)
  }

  // This is lazy because the whole of f(this.run) is treated as the pass-by-name parameter
  def map[B](f: A => B): IOLazy[B] = {
    println("IOLazy map")
    IOLazy(f(this.run))
  }
}

object IOLazy {
  def apply[A](a: => A): IOLazy[A] = new IOLazy(a)
}