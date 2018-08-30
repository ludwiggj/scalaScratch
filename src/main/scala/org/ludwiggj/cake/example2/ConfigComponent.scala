package org.ludwiggj.cake.example2

trait ConfigComponent {
  type Config
  def config: Config
}