package org.ludwiggj.exploring.taglessfinal.interpreter

import org.ludwiggj.exploring.taglessfinal.NoWrap
import org.ludwiggj.exploring.taglessfinal.bridge.ScalaToLanguageBridge
import org.ludwiggj.exploring.taglessfinal.language.Language

// We have defined what our meta-language can do. We expressed our problems in the language.
// Now it’s time to make it run. For instance with an interpreter like this.
object LanguageInterpreter {

  // Now we just implement the interface
  val interpret: Language[NoWrap] = new Language[NoWrap] {
    override def number(v: Int): NoWrap[Int] = v

    override def increment(a: NoWrap[Int]): NoWrap[Int] = a + 1

    override def add(a: NoWrap[Int], b: NoWrap[Int]): NoWrap[Int] = a + b

    override def text(v: String): NoWrap[String] = v

    override def toUpper(a: NoWrap[String]): NoWrap[String] = a.toUpperCase

    override def concat(a: NoWrap[String], b: NoWrap[String]): NoWrap[String] = a + " " + b

    override def toString(v: NoWrap[Int]): NoWrap[String] = v.toString
  }
}

object LanguageInterpreterExample {
  def main(args: Array[String]): Unit = {

    val expr1: ScalaToLanguageBridge[String] = ScalaToLanguageBridge.buildComplexExpression("Result is", 10, 1)

    println(expr1.apply(LanguageInterpreter.interpret))
  }
}