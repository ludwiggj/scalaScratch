package org.ludwiggj.cake.animals.existential.version4.pets

trait PetModule {
  type Pet <: PetLike

  trait PetLike { this: Pet =>
    def name: String
  }

  def Pet(name: String): Pet
}