package org.ludwiggj.exploring.taglessfinal.interpreter

import org.ludwiggj.exploring.taglessfinal.PrettyPrint
import org.ludwiggj.exploring.taglessfinal.bridge.ScalaToLanguageBridge
import org.ludwiggj.exploring.taglessfinal.language.Language

object LanguageInterpreterPrettyPrint {
  val interpret: Language[PrettyPrint] = new Language[PrettyPrint] {
    override def number(v: Int): PrettyPrint[Int] = s"($v)"

    override def increment(a: PrettyPrint[Int]): PrettyPrint[Int] = s"(inc $a)"

    override def add(a: PrettyPrint[Int], b: PrettyPrint[Int]): PrettyPrint[Int] = s"(+ $a $b)"

    override def text(v: String): PrettyPrint[String] = s"[$v]"

    override def toUpper(a: PrettyPrint[String]): PrettyPrint[String] = s"(toUpper $a)"

    override def concat(a: PrettyPrint[String], b: PrettyPrint[String]): PrettyPrint[String] = s"(concat $a $b)"

    override def toString(v: PrettyPrint[Int]): PrettyPrint[String] = s"(toString $v)"
  }
}

object LanguageInterpreterPrettyPrintExample {
  def main(args: Array[String]): Unit = {
    val expr1: ScalaToLanguageBridge[String] = ScalaToLanguageBridge.buildComplexExpression("Result is", 10, 1)

    println(s"Result (pretty print): ${expr1.apply(LanguageInterpreterPrettyPrint.interpret)}")
  }
}