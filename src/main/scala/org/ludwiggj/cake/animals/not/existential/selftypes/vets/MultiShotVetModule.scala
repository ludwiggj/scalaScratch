package org.ludwiggj.cake.animals.not.existential.selftypes.vets

import org.ludwiggj.cake.animals.not.existential.selftypes.pets.Pet
import scala.collection.mutable

trait MultiShotVetModule extends VetModule {

  // mutable is sin committed for testability
  val vaccinated: mutable.Set[Pet] = mutable.Set[Pet]()

  override def vaccinate(pet: Pet): String = {
    if (vaccinated.contains(pet)) {
      s"$vetName: I have already vaccinated ${pet.name}."
    } else {
      vaccinated.add(pet)
      s"$vetName: I've given ${pet.name} his shot."
    }
  }
}