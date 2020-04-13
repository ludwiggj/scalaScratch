package org.ludwiggj.fnprog.simplified.lesson_043.copying

case class BillingInfo(
                        creditCards: Seq[CreditCard]
                      )

case class Name(
                 firstName: String,
                 mi: String,
                 lastName: String
               )

case class User(
                 id: Int,
                 name: Name,
                 billingInfo: BillingInfo,
                 phone: String,
                 email: String
               )

case class CreditCard(
                       name: Name,
                       number: String,
                       month: Int,
                       year: Int,
                       cvv: String
                     )

object CopyWorkout {

  def main(args: Array[String]): Unit = {

    val hannahName = Name(
      firstName = "Hannah",
      mi = "C",
      lastName = "Jones"
    )

    val hannah1 = User(
      id = 1,
      name = hannahName,
      phone = "907-555-1212",
      email = "hannah@hannahjones.com",
      billingInfo = BillingInfo(
        creditCards = Seq(
          CreditCard(
            name = hannahName,
            number = "1111111111111111",
            month = 3,
            year = 2020,
            cvv = "123"
          )
        )
      )
    )

    println(s"Hannah              : $hannah1")

    // Update phone number
    val hannah2 = hannah1.copy(phone = "720-555-1212")

    println(s"Hannah  (new number): $hannah2")

    // New surname
    val newName = hannah2.name.copy(lastName = "Smith")

    val hannah3 = hannah2.copy(name = newName)
    println(s"Hannah (new surname): $hannah3")

    // Update credit card with new name
    val oldCC = hannah3.billingInfo.creditCards.head
    val newCC = oldCC.copy(name = newName)
    val newCCs = Seq(newCC)
    val hannah4 = hannah3.copy(billingInfo = BillingInfo(newCCs))
    println(s"Hannah (new surname): $hannah4")
  }
}