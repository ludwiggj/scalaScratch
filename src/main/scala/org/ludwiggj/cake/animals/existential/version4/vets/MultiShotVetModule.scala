package org.ludwiggj.cake.animals.existential.version4.vets

import scala.collection.mutable

trait MultiShotVetModule extends VetModule {

  type Vet = MultiShotVet

  // mutable is sin committed for testability
  val vaccinated: mutable.Set[Pet] = mutable.Set[Pet]()

  class MultiShotVet(val name: String) extends VetLike {
    override def vaccinate(pet: Pet): String = {
      if (vaccinated.contains(pet)) {
        s"$name: I have already vaccinated ${pet.name}."
      } else {
        vaccinated.add(pet)
        s"$name: I've given ${pet.name} his shot."
      }
    }
  }

  override def Vet(name: String): MultiShotVet = new MultiShotVet(name)
}