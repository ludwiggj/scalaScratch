package org.ludwiggj.cookbook.lesson_20_1

case class Stock(symbol: String, company: String)

case class StockInstance(symbol: String, datetime: String, price: BigDecimal, volume: Long)

object NetworkUtils {
  def getUrlContent(url: String): String = ???
}

object StockUtils {
  def buildUrl(symbol: String): String = ???
  def getPrice(symbol: String, html: String): BigDecimal = ???
  def getVolume(symbol: String, html: String): Long = ???
  def getHigh(symbol: String, html: String): String = ???
  def getLow(symbol: String, html: String): String = ???
}

object DateUtils {
  def currentDate: String = ???
  def currentTime: String = ???
}

object Workout {
  private val symbol = "AAPL"
  val stock = new Stock(symbol, "Apple")
  val url = StockUtils.buildUrl(stock.symbol)
  val html = NetworkUtils.getUrlContent(url)

  val stockInstance = StockInstance(
    symbol,
    DateUtils.currentDate,
    StockUtils.getPrice(symbol, html),
    StockUtils.getVolume(symbol, html)
  )
}