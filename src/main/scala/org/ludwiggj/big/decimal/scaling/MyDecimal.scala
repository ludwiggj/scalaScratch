package org.ludwiggj.big.decimal.scaling

case class MyBigDecimal(value: BigDecimal)

object MyBigDecimal {
  def apply(v: BigDecimal): MyBigDecimal = {
    MyBigDecimal(v)
  }
}

object Workout {
  def main(args: Array[String]): Unit = {

    println("Start")
    val z = MyBigDecimal(BigDecimal(6.0))

    println(z.value)
    println("End")
  }
}