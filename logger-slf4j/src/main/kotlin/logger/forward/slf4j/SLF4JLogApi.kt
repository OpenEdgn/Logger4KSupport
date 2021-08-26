package logger.forward.slf4j

import com.github.openEdgn.logger4k.api.ILogApi
import com.github.openEdgn.logger4k.api.ILogOutputApi

class SLF4JLogApi : ILogApi {

    override val name = "SlF4JLog"

    override fun init(): ILogOutputApi {
        return SLF4JLogOutputApi()
    }
}
