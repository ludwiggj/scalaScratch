package org.ludwiggj.dsl

import scala.collection.mutable

class Dfa {
  var states = Seq.empty[State]
  var finalStates = Seq.empty[State]
  val transition = new Transition
  val transitionMap = transition.transitionMap
  var currentState: Option[State] = None
  var input: String = ""

  def states(stateSeq: Seq[State]): Unit = {
    states = stateSeq
  }

  def finalStates(stateSeq: Seq[State]): Unit = {
    finalStates = stateSeq
  }

  def transitions(f: Transition => Unit): Dfa = {
    f(transition)
    this
  }

  def startFrom(start: State): Dfa = {
    currentState = Some(start)
    this
  }

  def withInput(input:String):Dfa = {
    this.input = input
    this
  }

  def run : Boolean = {
    input.foreach { ch =>
      transitionMap.get( (currentState.get, ch) ) match {
        case Some(state) => currentState = Some(state)
        case None =>
          throw new Exception(
            s"You must provide transition function for input '$ch' when state is ${currentState.get.toString}"
          )
      }
    }

    finalStates contains currentState.get
  }
}

class Transition {
  val transitionMap: mutable.HashMap[(State, Char), State] = new mutable.HashMap()

  def on(ch: Char): TransitionFrom = new TransitionFrom(this, ch)
}

class TransitionFrom(val tr: Transition, val ch: Char) {
  def from(s: State): TransitionTo = new TransitionTo(tr, ch, s)
}

class TransitionTo(val tr: Transition, val ch: Char, val from: State) {
  def to(s: State): Unit = {
    tr.transitionMap += (from, ch) -> s
  }
}

object Dfa {
  def newDfa(f: Dfa => Unit): Dfa = {
    val dfa = new Dfa
    f(dfa)
    dfa
  }
}