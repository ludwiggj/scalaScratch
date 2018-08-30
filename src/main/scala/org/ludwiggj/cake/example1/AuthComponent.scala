package org.ludwiggj.cake.example1

trait AuthComponent extends StorageComponent {
  def authenticate(id: Int, hash: Vector[Byte]): Boolean =
    retrieveUser(id) map { _.hash == hash } getOrElse false
}