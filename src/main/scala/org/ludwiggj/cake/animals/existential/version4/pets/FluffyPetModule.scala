package org.ludwiggj.cake.animals.existential.version4.pets

trait FluffyPetModule extends PetModule {

  type Pet = FluffyPet

  class FluffyPet(nom: String) extends PetLike {
    override def name: String = s"Fluffy $nom"
    override def toString: String = name
  }

  override def Pet(name: String) = new FluffyPet(name)
}