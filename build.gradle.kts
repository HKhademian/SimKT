plugins {
  kotlin("jvm") version "1.3.72"
  `java-library`
}

repositories {
  jcenter()
}

dependencies {
  implementation(platform(kotlin("bom")))
  implementation(kotlin("stdlib-jdk8"))

  testImplementation(kotlin("test"))
  testImplementation(kotlin("test-junit"))
}
