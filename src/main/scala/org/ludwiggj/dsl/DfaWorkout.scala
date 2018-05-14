package org.ludwiggj.dsl

import org.ludwiggj.dsl.Dfa.newDfa

object DfaWorkout {
  def main(args: Array[String]): Unit = {
    val dfa = newDfa { dfa =>
      dfa states {
        Seq(S0, S1, S2, S3)
      }

      dfa finalStates {
        Seq(S2)
      }

      dfa transitions { transition =>
        transition on '0' from S0 to S1
        transition on '1' from S0 to S3
        transition on '0' from S1 to S2
        transition on '1' from S1 to S1
        transition on '0' from S2 to S2
        transition on '1' from S2 to S1
        transition on '0' from S3 to S3
        transition on '1' from S3 to S3
      }
    }

    println((dfa startFrom S0 withInput "110101011110110110000").run)
    println((dfa startFrom S0 withInput "010101011110110110000").run)
    println((dfa startFrom S0 withInput "010101011110110110001").run)
  }
}
