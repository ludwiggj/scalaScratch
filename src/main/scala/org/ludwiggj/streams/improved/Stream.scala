package org.ludwiggj.streams.improved

// Definitions of Stream, Empty and Cons are as before
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
          f(h(), go(t(), z)(f))
        }
        case _ => z
      }
    }

    go(this, z)(f)
  }

  def exists(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  def map[B](f: A => B): Stream[B] = {
    foldRight(Stream.empty[B])((h, t) => {
      Stream.cons[B](Stream.evaluating(f(h)), t)
    })
  }

  def filter(f: A => Boolean): Stream[A] =
    foldRight(Stream.empty[A])((h, t) => {
      if (f(h)) {
        Stream.cons(h, t)
      } else {
        t
      }
    })
}

case object Empty extends Stream[Nothing]

// h and t are head and tail thunks
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

// Companion object is new...
object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: Seq[A]): Stream[A] = {
    if (as.isEmpty) {
      empty
    } else {
      cons(evaluating(as.head), apply(as.tail))
    }
  }

  def evaluating[A](f: => A): A = {
    val evaluated = f
    println(s"Evaluated $evaluated")
    evaluated
  }
}