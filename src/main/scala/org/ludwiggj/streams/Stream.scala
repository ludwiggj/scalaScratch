package org.ludwiggj.streams

sealed trait Stream[+A] {

  def toList: List[A] = {
    @annotation.tailrec
    def go(s: Stream[A], acc: List[A]): List[A] = s match {
      case Cons(h, t) => go(t(), h() :: acc)
      case _ => acc
    }

    go(this, List()).reverse
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = {

    def go(s: Stream[A], z: => B)(f: (A, => B) => B): B = {
      s match {
        case Cons(h, t) => {
          val head = h()
          println(s"Evaluating $head")
          f(h(), go(t(), z)(f))
        }
        case _ => z
      }
    }

    go(this, z)(f)
  }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  def empty[B]: Stream[B] = Empty

  def map[B](f: A => B): Stream[B] = {
    foldRight(this.empty[B])((h, t) => {
      Cons[B](() => f(h), () => t)
    })
  }

  def filter(f: A => Boolean): Stream[A] =
    foldRight(this.empty[A])((h, t) => {
      if (f(h)) {
        Cons(() => h, () => t)
      } else {
        t
      }
    })
}

case object Empty extends Stream[Nothing]

// h and t are head and tail thunks
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]
