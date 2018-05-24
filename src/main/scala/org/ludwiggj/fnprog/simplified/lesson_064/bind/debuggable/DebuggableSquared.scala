package org.ludwiggj.fnprog.simplified.lesson_64_bind.debuggable

case class DebuggableSquared(value: Int, msg: String) {

  def map(f: Int => Int): DebuggableSquared = {
    println(s"\n>>>> entered map $value >>>>")
    println(s"map (b4): this val: ${value}")
    println(s"map (b4): this msg: (${msg})")
    val nextValue = f(value) //Int
    println(s"<<<< leaving map $value <<<<\n")
    // there is no `nextValue.msg`
    println(s"map (after): next val: ${nextValue}")
    println("<<<< map exiting <<<<\n")
    DebuggableSquared(nextValue, msg)
  }

  def flatMap(f: Int => DebuggableSquared): DebuggableSquared = {
    println(s"\n>>>> entered fmap $value >>>>")
    println(s"fmap (b4): this val: ${value}")
    println(s"fmap (b4): this msg: (${msg})")
    val nextValue = f(value)
    println(s"<<<< leaving fmap $value <<<<\n")
    println(s"fmap (after): next val: ${nextValue.value}")
    println(s"fmap (after): this msg: (${msg})")
    println(s"fmap (after): next msg: \n(${nextValue.msg})")
    println("<<<< fmap exiting <<<<\n")
    DebuggableSquared(nextValue.value, msg + "\n" + nextValue.msg)
  }
}

object DebuggableSquaredSquaredExample {

  def f(a: Int): DebuggableSquared = {
    println(s"\n[Calcuating f(a = $a)] (Double)")
    val result = a * 2
    DebuggableSquared(result, s"f: input: $a, result: $result")
  }

  def g(a: Int): DebuggableSquared = {
    println(s"\n[Calcuating g(a = $a)] (Treble)")
    val result = a * 3
    DebuggableSquared(result, s"g: input: $a, result: $result")
  }

  def h(a: Int): DebuggableSquared = {
    println(s"\n[Calcuating h(a = $a)] (Quadruple)")
    val result = a * 4
    DebuggableSquared(result, s"h: input: $a, result: $result")
  }

  def displayResult(ds: DebuggableSquared): Unit = {
    println("\n----- FINAL RESULT -----")
    println(s"final value: ${ds.value}")
    println(s"final msg: \n${ds.msg}")
  }

  def sugared(): Unit = {
    val finalResult = for {
      fResult <- f(100)
      gResult <- g(fResult)
      hResult <- h(gResult)
    } yield hResult

    displayResult(finalResult)
  }

  def desugared(): Unit = {
    val finalResult = f(100).flatMap(
      fResult => g(fResult).flatMap(
        gResult => h(gResult).map(
          hResult => hResult
        )
      )
    )

    displayResult(finalResult)
  }

  def main(args: Array[String]): Unit = {
    sugared()
    desugared()
  }
}