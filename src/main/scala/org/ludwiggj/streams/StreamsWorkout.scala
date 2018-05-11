package org.ludwiggj.streams

object StreamsWorkout {
  // Example based on https://vmayakumar.wordpress.com/2018/04/17/streams/
  def main(args: Array[String]): Unit = {
    def lists(): Unit = {
      val list = List(1, 2, 3, 4, 5)

      // With intermediate results
      println(list.map(_ + 25).filter(_ % 7 == 0))

      // Without intermediate lists
      var result: List[Int] = List()
      for (i <- 0 to list.length - 1) {
        if ((list(i) + 25) % 7 == 0) {
          result = list(i) :: result
        }
      }

      println(result)
    }

    def thunks(): Unit = {
      // Syntax () => A is a "thunk"
      def lazinessExAGetsEvaluated[A](a: () => A, b: () => A): A = a()

      // Using simplified syntax
      def lazinessExBGetsEvaluated[A](a: => A, b: => A): A = b

      println(
        lazinessExAGetsEvaluated(
          () => {
            println("A is being evaluated"); 10
          },
          () => {
            println("B is being evaluated"); 20
          }
        ))

      println(
        lazinessExBGetsEvaluated(
          () => {
            println("A is being evaluated"); 10
          },
          () => {
            println("B is being evaluated"); 20
          }
        ))

      println(
        lazinessExBGetsEvaluated(
          {
            println("A is being evaluated"); 10
          },
          {
            println("B is being evaluated"); 20
          }
        ))
    }

    def streams(): Unit = {
      val streamOf4 = Cons(() => 1, () => Cons(() => 2, () => Cons(() => 3, () => Cons(() => 4, () => Empty))))
      println(streamOf4.toList)
      println(s"Stream contains 2? [${streamOf4.exists(_ == 2)}]")
      println(s"Stream contains 6? [${streamOf4.exists(_ == 6)}]")

      println("Calculating doubled stream...")
      val doubledStream = streamOf4.map(_ * 2)

      println("Doubled stream contains 4?")
      println(doubledStream.exists(_ == 4))

      println("Doubled stream contains 8?")
      println(doubledStream.exists(_ == 8))

      println(s"Doubled stream contains 9? [${doubledStream.exists(_ == 9)}]")

      println("Doubled stream, values > 5")
      val valuesLargerThan5 = doubledStream.filter(_ > 5)
      println(valuesLargerThan5.toList)

      println("Now the original expression... with 5 at the front")
      val streamOf5 = Cons(() => 5, () => streamOf4)
      println(streamOf5.toList)

      println(streamOf5.map(_ + 25).filter(_ % 7 == 0).toList)
    }

    // Start here
    lists()
    thunks()
    streams()
  }
}