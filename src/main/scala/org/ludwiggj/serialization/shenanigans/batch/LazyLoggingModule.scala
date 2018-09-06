package org.ludwiggj.serialization.shenanigans.batch

import org.apache.log4j.Logger

trait LazyLoggingModule {
  val loggerName: String
  val logger: Logger
}