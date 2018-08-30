package org.ludwiggj.cake.animals.existential.version4.vets

import org.ludwiggj.cake.animals.existential.version4.pets.PetModule

trait VetModule extends PetModule {
  type Vet <: VetLike

  trait VetLike { this: Vet =>
    def name: String
    def vaccinate(pet: Pet): String
  }

  def Vet(name: String): Vet
}