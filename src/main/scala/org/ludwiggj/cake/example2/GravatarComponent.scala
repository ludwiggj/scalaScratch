package org.ludwiggj.cake.example2

trait GravatarComponent extends StorageComponent {
  type Config <: GravatarConfig

  def avatarURL(user: User): String = ???

  trait GravatarConfig {
    def token: String
  }
}