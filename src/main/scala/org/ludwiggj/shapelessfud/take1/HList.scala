package org.ludwiggj.shapelessfud.take1

sealed trait HList

sealed trait HNil extends HList {
  def ::[T](value: T): T :: HNil = org.ludwiggj.shapelessfud.take1.::(value, HNil)
}
case object HNil extends HNil

case class ::[H, T <: HList](head: H, tail: T) extends HList {
  def ::[S](value: S): S :: H :: T = org.ludwiggj.shapelessfud.take1.::(value, this)
}