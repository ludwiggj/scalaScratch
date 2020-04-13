package org.ludwiggj.fnprog.simplified.lesson_027.implicitz

trait Animal

class Person(name: String) extends Animal {
  override def toString = "Person"
}

class Employee(name: String) extends Person(name) {
  override def toString = "Employee"
}

class Employer(name: String) extends Person(name) {
  override def toString = "Employer"
}

object Workout {
  def main(args: Array[String]): Unit = {
    def printPerson(b: Boolean)(implicit p: Person): Unit = if (b) println(p)

    implicit val p: Person = new Person("person")
    implicit val e: Employee = new Employee("employee")

    // Following prints Employee
    printPerson(b = true)

    // Following results in error
    // Error:(27, 16) ambiguous implicit values:
    // both value r of type org.ludwiggj.fnprog.simplified.implicitz.Employer
    // and value e of type org.ludwiggj.fnprog.simplified.implicitz.Employee
    // match expected type org.ludwiggj.fnprog.simplified.implicitz.Person

     // implicit val r: Employer = new Employer("employer")
     // printPerson(b = true)
  }
}
