import logger.forward.slf4j.SLF4JLogApi;

module logger4k.impl {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    requires org.slf4j;
    exports logger.forward.slf4j;
    opens logger.forward.slf4j to logger4k.core;
    provides  com.github.openEdgn.logger4k.api.ILogApi with SLF4JLogApi;
}
