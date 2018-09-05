package org.ludwiggj.mocking.trayt

import org.ludwiggj.mocking.{User, UserId}

trait UserRepository {

  def add(user: User): UserId

  def select(predicate: User => Boolean): Seq[User]
}

object UserRepository {
  def apply(db: DB[User, UserId]): UserRepository = new UserRepository {
    override def add(user: User): UserId = db.store(user)

    override def select(predicate: User => Boolean): Seq[User] = {
      db.all().map(db.retrieve(_)).filter(_.isDefined).map(_.get).filter(predicate)
    }
  }
}