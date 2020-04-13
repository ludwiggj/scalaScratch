package org.ludwiggj.fnprog.simplified.lesson_076.io

package object monad {
  object eager {
    def getLine: IOEager[String] = IOEager(scala.io.StdIn.readLine())
    def putStrLn(s: String): IOEager[Unit] = IOEager(println(s))
  }

  object lazee {
    def getLine: IOLazy[String] = IOLazy(scala.io.StdIn.readLine())
    def putStrLn(s: String): IOLazy[Unit] = IOLazy(println(s))
  }
}