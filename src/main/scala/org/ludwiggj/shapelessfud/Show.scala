package org.ludwiggj.shapelessfud

trait Show[T] {
  def show(value: T): String
}

object Show {
  def apply[T: Show]: Show[T] = implicitly[Show[T]]

  implicit val showString: Show[String] = str => str
  implicit val showDouble: Show[Double] = dbl => dbl.toString

  implicit def showMap[K: Show, V: Show]: Show[Map[K, V]] =
    m =>
      "{ " + m.map {
        case (k, v) => Show[K].show(k) + " -> " + Show[V].show(v)
      }.mkString(", ") + " }"
}