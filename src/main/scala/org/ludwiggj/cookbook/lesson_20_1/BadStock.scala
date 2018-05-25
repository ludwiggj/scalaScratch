package org.ludwiggj.cookbook.lesson_20_1

import scala.collection.mutable.ArrayBuffer

class BadStock(var symbol: String, var company: String,
               var price: BigDecimal, var volume: Long) {
  var html: String = _
  var high: Long = _
  var low: Long = _

  def buildUrl(stockSymbol: String): String = ???

  def getUrlContent(url: String): String = ???

  def setPriceFromHtml(html: String): Unit = ???

  def setVolumeFromHtml(html: String): Unit = ???

  def setHighFromHtml(html: String): Unit = ???

  def setLowFromHtml(html: String): Unit = ???

  // some dao-like functionality
  private val _history: ArrayBuffer[BadStock] = ???

  val getHistory = _history
}