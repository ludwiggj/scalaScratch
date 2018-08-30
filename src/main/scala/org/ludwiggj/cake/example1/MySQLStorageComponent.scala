package org.ludwiggj.cake.example1

trait MySQLStorageComponent extends StorageComponent {
  override def storeUser(user: User): Unit = ???
  override def retrieveUser(id: Int): Option[User] = ???

  case class User(id: Int, hash: Vector[Byte]) extends UserLike
}