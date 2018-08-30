package org.ludwiggj.cake.animals.existential.version1

trait RealVet extends Vet {
  override def vaccinate(pet: Pet): Unit = {
    println(s"Vaccinating ${pet.name}")
  }
}