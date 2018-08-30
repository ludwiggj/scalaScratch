package org.ludwiggj.cake.animals.not.existential.selftypes

import org.ludwiggj.cake.animals.not.existential.selftypes.pets.Pet
import org.ludwiggj.cake.animals.not.existential.selftypes.vets.VetModule

trait PetStore {
  this: VetModule =>

  val owner: String

  def sell(pet: Pet): String = {

    s"${vaccinate(pet)}\n$owner: ${pet.name} is now ready for sale!"
  }
}