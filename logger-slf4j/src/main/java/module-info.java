module logger4k.impl {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    requires org.slf4j;
    exports logger4k.impl.slf4j;
    opens logger4k.impl.slf4j to logger4k.core;
    provides com.github.openEdgn.logger4k.plugin.IPlugin with logger4k.LoggerPlugin;
    exports logger4k;
    opens logger4k to logger4k.core;
}
