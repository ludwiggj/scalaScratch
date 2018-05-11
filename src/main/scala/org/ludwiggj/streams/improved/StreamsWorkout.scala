package org.ludwiggj.streams.improved

object StreamsWorkout {
  // Example based on https://vmayakumar.wordpress.com/2018/04/17/streams/
  def main(args: Array[String]): Unit = {
    val streamOf4 = Stream(List(1, 2, 3, 4))
    println(streamOf4.toList)
    println(streamOf4.toList)

    println(s"Stream contains 2? [${streamOf4.exists(_ == 2)}]")
    println(s"Stream contains 6? [${streamOf4.exists(_ == 6)}]")

    println("Calculating doubled stream...")
    val doubledStream = streamOf4.map(_ * 2)

    println("Doubled stream contains 4?")
    println(doubledStream.exists(_ == 4))

    println("Doubled stream contains 8?")
    println(doubledStream.exists(_ == 8))

    println("Doubled stream contains 9?")
    println(doubledStream.exists(_ == 9))

    println("Doubled stream, values > 5")
    val valuesLargerThan5 = doubledStream.filter(_ > 5)
    println(valuesLargerThan5.toList)

    println("Now the original expression...")
    val streamOf5 = Stream(List(1, 2, 3, 4, 5))
    println(streamOf5.map(_ + 25).filter(_ % 7 == 0).toList)
  }
}