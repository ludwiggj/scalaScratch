package org.ludwiggj.mocking.trayt

trait DB[A, ID] {
  def store(a: A): ID

  def retrieve(id: ID): Option[A]

  def all(): Seq[ID]
}