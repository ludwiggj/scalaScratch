package org.ludwiggj.cake.animals.not.existential.selftypes.vets

import org.ludwiggj.cake.animals.not.existential.selftypes.pets.Pet

trait SingleShotVetModule extends VetModule {

  // Sin committed for testability
  var vaccinated: Option[Pet] = None

  override def vaccinate(pet: Pet): String = {
    vaccinated match {
      case Some(otherPet) =>
        s"$vetName: I cannot vaccinate ${pet.name} as I've already vaccinated ${otherPet.name}. That's your lot!"
      case _ =>
        vaccinated = Some(pet)
        s"$vetName: I've given ${pet.name} his shot."
    }
  }
}