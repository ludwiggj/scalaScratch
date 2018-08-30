package org.ludwiggj.cake.animals.existential.version2

object RunPetStores {
  def realPetStoreViaObject(): Unit = {
    object PetesPets extends PetStore with RealVet

    PetesPets.vaccinate(PetesPets.RealPet("burtie"))
    PetesPets.sell(PetesPets.RealPet("ern"))
  }

  def realPetStoreViaClass(): Unit = {
    class PinkPets extends PetStore with RealVet
    val pp = new PinkPets
    pp.vaccinate(pp.RealPet("pink pig"))
    pp.sell(pp.RealPet("another porker"))
  }

  // Inside the object (probably the best option)
  object RealPetStore extends PetStore with RealVet {
    def run(): Unit = {
      vaccinate(RealPet("freddie"))
    }
  }

  trait MockVet extends Vet {
    case class MockPet(name: String) extends PetLike
    type Pet = MockPet

    override def vaccinate(pet: Pet): Unit = println(s"MOCKED, so ${pet.name} needs to see a real vet")
  }

  object FakePetStore extends PetStore with MockVet {
    def run(): Unit = {
      vaccinate(MockPet("jason"))
    }
  }

  def main (args: Array[String] ): Unit = {
    realPetStoreViaObject()
    realPetStoreViaClass()
    RealPetStore.run()
    FakePetStore.run()
  }
}