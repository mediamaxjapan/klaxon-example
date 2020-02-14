group = "jp.ne.mmj"
version = "1.0.0"

plugins {
  `java-library`
  kotlin("jvm") version "1.3.61"
}

repositories {
  jcenter()
}

val kotlinVersion: String by project
val spekVersion: String by project

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.beust:klaxon:5.0.13")
  testImplementation("org.spekframework.spek2:spek-dsl-jvm:${spekVersion}")
  testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:${spekVersion}")
  testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks {
  test {
    useJUnitPlatform {
      includeEngines("spek2")
    }
  }
}
