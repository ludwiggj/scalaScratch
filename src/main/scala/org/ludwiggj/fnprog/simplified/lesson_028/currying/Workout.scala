package org.ludwiggj.fnprog.simplified.lesson_028.currying

object Workout {
  def plus(a: Int)(b: Int): Int = a + b

  def plus2: Int => Int = plus(2)(_)
  def plus3: Int => Int = plus(3)_
  def plus4: Int => Int = plus(4)

  def wrap(prefix: String)(html: String)(suffix: String): String = {
    prefix + html + suffix
  }
  val wrapWithDiv: String => String = wrap("<div>")(_: String)("</div>")
  val wrapWithPre: String => String = wrap("<pre>")(_)("</pre>")

  def add(x: Int, y: Int): Int = x + y

  // Underscore is superfluous
  val addFunction1: (Int, Int) => Int = add _

  // Compiler much be able to perform eta-expansion automatically
  val addFunction2: (Int, Int) => Int = add

  // Underscore is superfluous
  val addFunction3: Function2[Int, Int, Int] = add _

  // Compiler much be able to perform eta-expansion automatically
  val addFunction4: Function2[Int, Int, Int] = add

  val addCurried: Int => Int => Int = (add _).curried

  val addCurriedTwo: Int => Int = addCurried(2)

  def main(args: Array[String]): Unit = {
    println(plus2(4))
    println(plus2(3))
    println(plus3(4))
    println(plus3(3))
    println(plus4(4))
    println(plus4(3))
    println(wrapWithDiv("Hi"))
    println(wrapWithPre("Hi"))
    println(addFunction1(1, 2))
    println(addFunction2(1, 2))
    println(addFunction3(1, 2))
    println(addFunction4(1, 2))
    println((add _).isInstanceOf[Function2[_, _, _]])

    // Following line doesn't compile
    // Error:(36, 13) missing argument list for method add in object Workout
    // Unapplied methods are only converted to functions when a function type is expected.
    // You can make this conversion explicit by writing `add _` or `add(_,_)` instead of `add`.

    // println(add.isInstanceOf[Function2[_, _, _]])
    println((addCurried).isInstanceOf[Function1[_, _]])
    println(addCurried(10)(20))

    println((addCurriedTwo).isInstanceOf[Function1[_, _]])
    println(addCurriedTwo(4))
  }
}
