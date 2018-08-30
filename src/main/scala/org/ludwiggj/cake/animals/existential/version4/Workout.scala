package org.ludwiggj.cake.animals.existential.version4

import org.ludwiggj.cake.animals.existential.version4.pets.FluffyPetModule
import org.ludwiggj.cake.animals.existential.version4.vets.{MockedVetModule, MultiShotVetModule, SingleShotVetModule}
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._

object Workout {

  def dodgyPetStore(): Unit = {
    object RealPetStore extends PetStore with SingleShotVetModule with FluffyPetModule {
      override val vet = Vet("Dr One Shot")
      override val owner = "Dodgy Dave"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
    }

    import RealPetStore._

    println(sell(fluffyBen))
    println(vet.vaccinate(fluffyBob))
    println(sell(fluffyBill))
  }

  def nicePetStore(): Unit = {
    object RealPetStore extends PetStore with MultiShotVetModule with FluffyPetModule {
      override val vet = Vet("Dr Multi Shot")
      override val owner = "Sir Ranulph Fur"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
    }

    import RealPetStore._

    println(sell(fluffyBen))
    println(vet.vaccinate(fluffyBob))
    println(sell(fluffyBill))
    println(sell(fluffyBen))
  }

  def petStoreWithMockedVet(): Unit = {

    object RealPetStore extends PetStore with FluffyPetModule {
      type Vet = VetLike
      override val vet = mock(classOf[VetLike])

      override def Vet(name: String): RealPetStore.VetLike = vet

      override val owner = "Sir Mockney Oliver"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
      val fluffyBabe = Pet("Babe")
    }

    import RealPetStore._

    when(vet.vaccinate(fluffyBen)).thenReturn("Vaccinate called")

    println(sell(fluffyBen))
    println(vet.vaccinate(fluffyBob))
    println(sell(fluffyBill))
    println(sell(fluffyBen))

    verify(vet).vaccinate(fluffyBob)
    verify(vet).vaccinate(fluffyBabe)
  }

  def anotherPetStoreWithMockedVet(): Unit = {

    object RealPetStore extends PetStore with MockedVetModule with FluffyPetModule {
      override val vet = Vet("Mocked Vet")
      override val owner = "Sir Mockney Oliver II"

      val fluffyBen = Pet("Ben")
      val fluffyBob = Pet("Bob")
      val fluffyBill = Pet("Bill")
      val fluffyBabe = Pet("Babe")
    }

    import RealPetStore._

    expect(
      vet => {
        when(vet.vaccinate(any[Pet])).thenAnswer(i => s"${vet.name}: Vaccinated ${i.getArgument(0).toString}")
      }
    )

    println(sell(fluffyBen))
    println(vet.vaccinate(fluffyBob))
    println(sell(fluffyBill))
    println(sell(fluffyBen))

    postCheck(
      vet => {
        verify(vet).vaccinate(fluffyBob)
//        verify(vet).vaccinate(fluffyBabe)
      }
    )
  }

  def main(args: Array[String]): Unit = {
    dodgyPetStore()
    println()
    nicePetStore()
    println()
//    petStoreWithMockedVet()
//    println()
    anotherPetStoreWithMockedVet()
  }
}