package org.ludwiggj.cake.animals.existential.version1

object PetesPets extends PetStore with RealVet {
  case class RealPet(name: String) extends PetLike
  type Pet = RealPet

  override def Pet(name: String): Pet = RealPet(name)
}

class PinkPets extends PetStore with RealVet {
  case class PinkPet(name: String) extends PetLike
  type Pet = PinkPet

  override def Pet(name: String): Pet = PinkPet(name)
}

object RunStore {
  def main (args: Array[String] ): Unit = {
    PetesPets.vaccinate(PetesPets.Pet("burt"))
    PetesPets.sell(PetesPets.RealPet("ernie"))

    val pp = new PinkPets
    pp.vaccinate(pp.Pet("pink big birdz"))
    pp.sell(pp.PinkPet("another pink big bird"))
  }
}