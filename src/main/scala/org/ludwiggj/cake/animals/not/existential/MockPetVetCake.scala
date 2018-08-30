package org.ludwiggj.cake.animals.not.existential

import org.mockito.Mockito._

case class Pet(name: String)

trait Vet {
  def vaccinate(pet: Pet): Unit
}

trait PetStore extends Vet {
  def sell(pet: Pet): Unit = {
    vaccinate(pet)
    println(s"${pet.name} is now ready for sale!")
  }
}

trait MockVet extends Vet {
  val mockVet: Vet

  override def vaccinate(pet: Pet): Unit = mockVet.vaccinate(pet)
}

object RunPetStores {

  def main(args: Array[String]): Unit = {

    object MockPetStore extends PetStore with MockVet {
      val mockVet = mock(classOf[Vet])
    }

    // This fails as expected
    val bob = Pet("bob")
    //    MockPetStore.vaccinate(bob)
    //    verifyZeroInteractions(MockPetStore.mockVet)

    val steve = Pet("steve")
    MockPetStore.vaccinate(steve)

    // This passes
    verify(MockPetStore.mockVet).vaccinate(steve)

    // This fails
    verify(MockPetStore.mockVet).vaccinate(bob)
  }
}