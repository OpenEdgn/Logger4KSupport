import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
}
java.sourceCompatibility = JavaVersion.VERSION_11

java {
    modularity.inferModulePath.set(true)
}

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDirectory.set(compileKotlin.destinationDirectory.get())

val slf4jVersion = "1.7.31"

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    compileOnly("com.github.OpenEdgn.Logger4K:logger-core:2.0.2")
    compileOnly("org.slf4j:slf4j-api:$slf4jVersion")
    testImplementation("org.slf4j:slf4j-api:$slf4jVersion")
    testImplementation("ch.qos.logback:logback-core:1.2.5")
    testImplementation("ch.qos.logback:logback-core:1.2.5")
    testImplementation("com.github.OpenEdgn.Logger4K:logger-core:2.0.2")
    testImplementation("org.slf4j:slf4j-simple:$slf4jVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Zip> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE // allow duplicates
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = rootProject.version.toString()
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
