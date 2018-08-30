package org.ludwiggj.cake.animals.not.existential.selftypes.vets

import org.ludwiggj.cake.animals.not.existential.selftypes.pets.Pet

trait VetModule {
  val vetName: String

  def vaccinate(pet: Pet): String
}