buildscript {
    dependencies {
        classpath("mysql:mysql-connector-java:8.0.13")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    id("org.flywaydb.flyway").version("5.2.4")
    application
}

repositories {
    jcenter()
}

val ktor_version = "1.1.1"
val logback_version = "1.2.3"
val koin_version = "1.0.2"
val jasync_version = "0.8.64"
val jackson_version = "2.9.2"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")

    implementation("org.koin:koin-ktor:$koin_version")
    implementation("org.koin:koin-logger-slf4j:$koin_version")

    implementation("com.github.jasync-sql:jasync-mysql:$jasync_version")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

flyway {
    url = "jdbc:mysql://localhost:3306/cinemadb"
    user = "user"
    password = "password"
}

application {
    mainClassName = "cinemadb.AppKt"
}
