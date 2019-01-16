plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    application
}

repositories {
    jcenter()
}

val ktor_version = "1.1.1"
val logback_version = "1.2.3"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.ktor:ktor-server-netty:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "cinemadb.AppKt"
}
