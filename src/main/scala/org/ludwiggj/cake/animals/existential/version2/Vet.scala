package org.ludwiggj.cake.animals.existential.version2

trait Vet {
  type Pet <: PetLike

  def vaccinate(pet: Pet): Unit

  trait PetLike {
    def name: String
  }
}