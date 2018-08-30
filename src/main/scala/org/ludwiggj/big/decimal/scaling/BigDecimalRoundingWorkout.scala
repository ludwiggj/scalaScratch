package org.ludwiggj.big.decimal.scaling

import scala.math.BigDecimal.RoundingMode
import scala.math.BigDecimal.RoundingMode.RoundingMode

object BigDecimalRoundingWorkout {
  def main(args: Array[String]): Unit = {
    testRun(Seq(
      RoundingMode.HALF_UP,
      RoundingMode.HALF_DOWN,
      RoundingMode.HALF_EVEN,
      RoundingMode.FLOOR,
      RoundingMode.CEILING
    ))
  }

  private def testRun(roundingModes: Seq[RoundingMode]): Unit = {
    bigD("2.104", roundingModes)
    bigD("2.105", roundingModes)
    bigD("2.106", roundingModes)
    bigD("2.109", roundingModes)
    bigD("2.110", roundingModes)
    bigD("2.111", roundingModes)
    bigD("2.114", roundingModes)
    bigD("2.115", roundingModes)
    bigD("2.116", roundingModes)
    bigD("2.119", roundingModes)
    bigD("2.120", roundingModes)
    bigD("2.121", roundingModes)
    bigD("2.124", roundingModes)
    bigD("2.125", roundingModes)
    bigD("2.126", roundingModes)
    bigD("2.129", roundingModes)
    bigD("2.130", roundingModes)
    bigD("2.131", roundingModes)
  }

  private def bigD(value: String, roundingModes: Seq[RoundingMode]): Unit = {
    println(s"Value $value, rounding: " +
      roundingModes.map(rm => s"[$rm, ${BigDecimal(value).setScale(2, rm)}]").mkString(", "))
  }
}
