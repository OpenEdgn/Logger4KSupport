package org.slf4j.impl

import com.github.openEdgn.logger4k.LoggerFactory
import com.github.openEdgn.logger4k.LoggerLevel
import org.slf4j.Marker
import org.slf4j.event.Level
import org.slf4j.helpers.LegacyAbstractLogger
import org.slf4j.helpers.MessageFormatter

class SimpleLogger(name: String) : LegacyAbstractLogger() {
    private val logger = LoggerFactory.getLogger(name)

    init {
        super.name = name
    }

    override fun isTraceEnabled() = logger.level.level <= LoggerLevel.TRACE.level

    override fun isDebugEnabled() = logger.level.level <= LoggerLevel.DEBUG.level

    override fun isInfoEnabled() = logger.level.level <= LoggerLevel.INFO.level

    override fun isWarnEnabled() = logger.level.level <= LoggerLevel.WARN.level

    override fun isErrorEnabled() = logger.level.level <= LoggerLevel.ERROR.level

    override fun getFullyQualifiedCallerName(): String? {
        return null
    }

    override fun handleNormalizedLoggingCall(
        level: Level,
        marker: Marker?,
        msg: String?,
        arguments: Array<out Any>?,
        throwable: Throwable?
    ) {
        // slf4j marker
        val markerStr = if (marker != null) {
            " [${marker.name}] "
        } else {
            ""
        }
        val message = markerStr + (MessageFormatter.basicArrayFormat(msg, arguments) ?: "")
        if (throwable != null) {
            when (level) {
                Level.ERROR -> logger.errorThrowable(message, throwable)
                Level.WARN -> logger.warnThrowable(message, throwable)
                Level.INFO -> logger.infoThrowable(message, throwable)
                Level.DEBUG -> logger.debugThrowable(message, throwable)
                Level.TRACE -> logger.traceThrowable(message, throwable)
            }
        } else {
            when (level) {
                Level.ERROR -> logger.error(message)
                Level.WARN -> logger.warn(message)
                Level.INFO -> logger.info(message)
                Level.DEBUG -> logger.debug(message)
                Level.TRACE -> logger.trace(message)
            }
        }
    }
}
