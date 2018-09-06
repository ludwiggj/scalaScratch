package org.ludwiggj.serialization.shenanigans.batch

import org.apache.log4j.Logger

trait LazyLoggingStub extends LazyLoggingModule with Serializable  {
  override val loggerName = "Stubbed logger"

  @transient
  override val logger: Logger = new Logger("my logger") {
    override def debug(message: Any): Unit = {
      println(s"my logger DEBUG called: [$message]")
    }

    override def info(message: Any): Unit = {
      println(s"my logger INFO called: [$message]")
    }

    override def error(message: Any, t: Throwable): Unit = {
      println(s"my logger ERROR called: [$message] exception: [${t.getMessage}]")
    }
  }
}