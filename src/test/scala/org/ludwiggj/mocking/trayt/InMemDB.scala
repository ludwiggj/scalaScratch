package org.ludwiggj.mocking.trayt

import org.ludwiggj.mocking.User

class InMemDB extends DB[User, String] {

  val map = collection.mutable.Map.empty[String, User]

  override def store(a: User): String = {
    map.update(a.id, a)

    a.id
  }

  override def retrieve(id: String): Option[User] = map.get(id)

  override def all(): Seq[String] = map.keySet.toSeq
}