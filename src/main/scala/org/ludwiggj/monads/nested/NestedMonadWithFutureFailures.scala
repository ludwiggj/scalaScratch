package org.ludwiggj.monads.nested

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.implicitConversions

object NestedMonadWithFutureFailures {
  def getIdByEmail(email: String): Future[Int] =
    if (email.endsWith("@gmail.com")) {
      Future.successful(1)
    } else if (email.endsWith("@yahoo.com")) {
      Future.successful(2)
    } else {
      Future.failed(new Exception("No User with given email"))
    }

  def getPostsById(id: Int): Future[List[String]] = {
    if (id == 1) {
      Future.successful(List("Post title 1", "Post title 2"))
    } else {
      Future.failed(new Exception("No posts found"))
    }
  }

  def getPostsByEmail(email: String) = {
    for {
      id <- getIdByEmail(email)
      posts <- getPostsById(id)
    } yield posts
  } recover {
//    case e: Exception => Future.successful(e.getMessage)
    case e: Exception => e.getMessage
  }

  //  def getPostsByEmailAlternative(email: String) = {
  //    // Doesn't work well...
  //    for {
  //      eitherAnIdValue <- getIdByEmail(email)
  //    } yield for {
  //      id <- eitherAnIdValue
  //    } yield for {
  //      posts <- getPostsById(id)
  //    } yield posts
  //  }

  def main(args: Array[String]): Unit = {
    println(Await.result(getPostsByEmail("mee@gmail.com"), 3.seconds))
    println(Await.result(getPostsByEmail("mee@bt.com"), 3.seconds))
    println(Await.result(getPostsByEmail("mee@yahoo.com"), 3.seconds))


    //    println(Await.result(getPostsByEmailAlternative("mee@gmail.com"), 3.seconds))
    //    println(Await.result(getPostsByEmailAlternative("mee@bt.com"), 3.seconds))
    //    println(Await.result(getPostsByEmailAlternative("mee@yahoo.com"), 3.seconds))
  }
}
