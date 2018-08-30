package org.ludwiggj.cake.animals.existential.version2

trait PetStore extends Vet {
  def sell(pet: Pet): Unit = {
    vaccinate(pet)
    println(s"${pet.name} is now ready for sale!")
  }
}