package org.ludwiggj.lens

import org.ludwiggj.lens.model.{Address, Street}

case class Lens[A, B](
                       get: A => B,
                       set: (A, B) => A
                     )

object LensWorkout {

  def main(args: Array[String]): Unit = {
    val streetNumberLens = Lens[Street, Int](
      get = _.number,
      set = (a, b) => a.copy(number = b)
    )

    val st = Street("Constable Road", 15)

    println(streetNumberLens.get(st))
    println(streetNumberLens.set(st, 9))

    def compose[Outer, Inner, Value](outer: Lens[Outer, Inner], inner: Lens[Inner, Value]) = Lens[Outer, Value](
      get = outer.get andThen inner.get,
      set = (obj, value) => outer.set(obj, inner.set(outer.get(obj), value))
    )

    val addressStreetLens = Lens[Address, Street](
      get = _.street,
      set = (a, b) => a.copy(street = b)
    )

    val addressStreetNumberLens: Lens[Address, Int] = compose(addressStreetLens, streetNumberLens)

    val addr = Address("UK", "Ipswich", st)

    println(addressStreetNumberLens.get(addr))
    println(addressStreetNumberLens.set(addr, 10))
  }
}
