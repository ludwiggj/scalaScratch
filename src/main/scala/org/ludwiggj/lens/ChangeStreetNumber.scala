package org.ludwiggj.lens

import org.ludwiggj.lens.model.{Account, Address, Street, User}

object ChangeStreetNumber {
  def changeStreetNumber(account: Account,
                         int: Int): Account = {
    account.copy(
      user = account.user.copy(
        address = account.user.address.copy(
          street = account.user.address.street.copy(
            number = int
          )
        )
      )
    )
  }

  def main(args: Array[String]): Unit = {

    val before = Account(1, User(
      1, Address(
        "UK", "Ipswich", Street(
          "Constable Road", 15
        )
      )
    ), isActive = true)

    val after: Account = changeStreetNumber(before, 17)

    println(before)
    println(after)
  }
}
