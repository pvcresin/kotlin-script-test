import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.2.51"
}

group = "pvcresin.kotlin-script-test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val outDir = "out"

tasks {
    "copy" (Copy::class){
        from("src")
        into(outDir)
    }
    "overwrite" {
        ant.withGroovyBuilder {
            "replace"("file" to "$outDir/copy.data", "token" to "@@data@@", "value" to "overwrite")
        }
    }
    "clean"(Delete::class) {
        delete(outDir)
    }
}
