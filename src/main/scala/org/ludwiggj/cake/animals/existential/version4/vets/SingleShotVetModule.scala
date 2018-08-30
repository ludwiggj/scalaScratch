package org.ludwiggj.cake.animals.existential.version4.vets

trait SingleShotVetModule extends VetModule {

  type Vet = SingleShotVet

  // Sin committed for testability
  var vaccinated: Option[Pet] = None

  class SingleShotVet(val name: String) extends VetLike {
    override def vaccinate(pet: Pet): String = {
      vaccinated match {
        case Some(otherPet) =>
          s"$name: I cannot vaccinate ${pet.name} as I've already vaccinated ${otherPet.name}. That's your lot!"
        case _ =>
          vaccinated = Some(pet)
          s"$name: I've given ${pet.name} his shot."
      }
    }
  }

  override def Vet(name: String): SingleShotVet = new SingleShotVet(name)
}