package org.ludwiggj.fnprog.simplified.lesson_070.bindInFor.debugged

case class Debuggable(value: Int, msg: String) {

  def map(f: Int => Int): Debuggable = {
    println(s"\n>>>> entered map $value >>>>")
    println(s"map (b4): this val: $value")
    println(s"map (b4): this msg: ($msg)")
    val nextValue = f(value) //Int
    println(s"<<<< leaving map $value <<<<\n")
    // there is no `nextValue.msg`
    println(s"map (after): next val: $nextValue")
    println("<<<< map exiting <<<<\n")
    Debuggable(nextValue, msg)
  }

  def flatMap(f: Int => Debuggable): Debuggable = {
    println(s"\n>>>> entered fmap $value >>>>")
    println(s"fmap (b4): this val: $value")
    println(s"fmap (b4): this msg: ($msg)")
    val nextValue = f(value)
    println(s"<<<< leaving fmap $value <<<<\n")
    println(s"fmap (after): next val: ${nextValue.value}")
    println(s"fmap (after): this msg: ($msg)")
    println(s"fmap (after): next msg: \n(${nextValue.msg})")
    println("<<<< fmap exiting <<<<\n")
    Debuggable(nextValue.value, msg + "\n" + nextValue.msg)
  }
}

object DebuggableWorkout {

  def f(a: Int): Debuggable = {
    println(s"\n[f(a = $a)] (Double)")
    val result = a * 2
    Debuggable(result, s"f: input: $a, result: $result")
  }

  def g(a: Int): Debuggable = {
    println(s"\n[g(a = $a)] (Treble)")
    val result = a * 3
    Debuggable(result, s"g: input: $a, result: $result")
  }

  def h(a: Int): Debuggable = {
    println(s"\n[h(a = $a)] (Quadruple)")
    val result = a * 4
    Debuggable(result, s"h: input: $a, result: $result")
  }

  def displayResult(ds: Debuggable): Unit = {
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