package org.ludwiggj.cake.example2

trait MySQLStorageComponent extends StorageComponent with ConfigComponent {
  type Config <: MySQLConfig

  override def storeUser(user: User): Unit = ???

  override def retrieveUser(id: Int): Option[User] = ???

  case class User(id: Int, hash: Vector[Byte]) extends UserLike

  trait MySQLConfig {
    def mysqlHost: String

    def mysqlPort: Int
  }
}