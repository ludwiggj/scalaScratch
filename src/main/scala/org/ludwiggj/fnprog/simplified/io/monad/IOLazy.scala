package org.ludwiggj.fnprog.simplified.io.monad

class IOLazy[A] private(constructorCodeBlock: => A) {
  def run: A = constructorCodeBlock

  def flatMap[B](f: A => IOLazy[B]): IOLazy[B] = IOLazy(f(run).run)

  def map[B](f: A => B): IOLazy[B] = IOLazy(f(run))
}

object IOLazy {
  def apply[A](a: => A): IOLazy[A] = new IOLazy(a)
}