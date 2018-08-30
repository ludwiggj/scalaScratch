package org.ludwiggj.cake.animals.existential.version1

trait PetStore extends Vet {
  def Pet(name: String): Pet

  def sell(pet: Pet): Unit = {
    vaccinate(pet)
    println(s"${pet.name} is now ready for sale!")
  }
}