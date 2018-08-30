package org.ludwiggj.cake.animals.not.existential.selftypes

import org.ludwiggj.cake.animals.not.existential.selftypes.pets.Pet
import org.ludwiggj.cake.animals.not.existential.selftypes.vets.{MockedVetModule, MultiShotVetModule, SingleShotVetModule}
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

object NonExistentialWorkout {

  def dodgyPetStore(): Unit = {
    object RealPetStore extends PetStore with SingleShotVetModule {
      override val vetName = "Dr One Shot"
      override val owner = "Dodgy Dave"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
    }

    import RealPetStore._

    println(sell(fluffyBen))
    println(vaccinate(fluffyBob))
    println(sell(fluffyBill))
  }

  def nicePetStore(): Unit = {
    object RealPetStore extends PetStore with MultiShotVetModule {
      override val vetName = "Dr Multi Shot"
      override val owner = "Sir Ranulph Fur"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
    }

    import RealPetStore._

    println(sell(fluffyBen))
    println(vaccinate(fluffyBob))
    println(sell(fluffyBill))
    println(sell(fluffyBen))
  }

  def petStoreWithMockedVet(): Unit = {
    object RealPetStore extends PetStore with MockedVetModule {
      override val owner = "Sir Mockney Oliver II"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
      val fluffyBabe = Pet("Babe")
    }

    import RealPetStore._

    expect(
      vet => {
        when(vet.vetName).thenReturn("Mocked Vet")
        when(vet.vaccinate(any[Pet])).thenAnswer(i => s"${vet.vetName}: Vaccinated ${i.getArgument(0).toString}")
      }
    )

    println(sell(fluffyBen))
    println(vaccinate(fluffyBob))
    println(sell(fluffyBill))
    println(sell(fluffyBen))

    postCheck(
      vet => {
        verify(vet).vaccinate(fluffyBob)
        verify(vet).vaccinate(fluffyBabe)
      }
    )
  }

  def main(args: Array[String]): Unit = {
    dodgyPetStore()
    println()
    nicePetStore()
    println()
    petStoreWithMockedVet()
  }
}