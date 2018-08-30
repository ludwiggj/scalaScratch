package org.ludwiggj.cake.animals.existential.version2

trait RealVet extends Vet {
  case class RealPet(name: String) extends PetLike
  type Pet = RealPet

  override def vaccinate(pet: Pet): Unit = {
    println(s"Vaccinating ${pet.name}")
  }
}