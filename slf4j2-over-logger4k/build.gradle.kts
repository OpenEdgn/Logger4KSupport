import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
}
java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDirectory.set(compileKotlin.destinationDirectory.get())

java {
    modularity.inferModulePath.set(true)
}

val slf4jVersion = "2.0.0-alpha2"

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    compileOnly("com.github.OpenEdgn.Logger4K:logger-core:2.2.1")
    compileOnly("org.slf4j:slf4j-api:$slf4jVersion")
    testImplementation("org.slf4j:slf4j-api:$slf4jVersion")
    testImplementation("com.github.OpenEdgn.Logger4K:logger-core:2.2.1")
    testImplementation("com.github.OpenEdgn.Logger4K:logger-console:2.2.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.platform:junit-platform-launcher:1.8.1")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = rootProject.version.toString()
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
    repositories {
        mavenLocal()
    }
}
