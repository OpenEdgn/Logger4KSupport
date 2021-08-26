module logger4k.proxy.slf4j {
    requires kotlin.reflect;
    requires kotlin.stdlib;
    requires logger4k.core;
    requires org.slf4j;
    exports org.slf4j.simple;
    provides org.slf4j.spi.SLF4JServiceProvider with org.slf4j.simple.SimpleServiceProvider;
    opens org.slf4j.simple to logger4k.core;
    exports org.slf4j.impl;
    opens org.slf4j.impl to logger4k.core;

}
