package org.ludwiggj.mocking.typeclass

import org.ludwiggj.mocking.User

trait Writer[A] {
  def put(a: A): Boolean
}

object UserStorage {

  implicit object UserWriter extends Writer[User] {
    override def put(a: User): Boolean = {
      println(s"Putting User ${a.id}")
      true
    }
  }

  object Writer {
    def apply[A](implicit writer: Writer[A]): Writer[A] =
      writer
  }

  implicit class PutOps[T](data: T) {
    def put(implicit writer: Writer[T]): Boolean =
      writer.put(data)
  }

  def main(args: Array[String]): Unit = {
    implicit val path = "somewhere on the file system"

    val user: User = User("Joe")

    UserWriter.put(user)

    Writer[User].put(user)

    user.put
  }
}