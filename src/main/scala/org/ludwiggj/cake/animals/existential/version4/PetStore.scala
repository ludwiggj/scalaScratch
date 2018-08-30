package org.ludwiggj.cake.animals.existential.version4

import org.ludwiggj.cake.animals.existential.version4.vets.VetModule

trait PetStore extends VetModule {
  val vet: Vet
  val owner: String

  def sell(pet: Pet): String = {

    s"${vet.vaccinate(pet)}\n$owner: ${pet.name} is now ready for sale!"
  }
}