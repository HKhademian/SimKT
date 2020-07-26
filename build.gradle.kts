plugins {
	kotlin("jvm") // version "1.3.72"
	`java-library`
}

repositories {
	jcenter()
}

dependencies {
	implementation(platform(kotlin("bom")))
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")

	implementation(kotlin("test"))
	implementation(kotlin("test-junit"))
}

java {
	sourceCompatibility = JavaVersion.VERSION_14
	targetCompatibility = JavaVersion.VERSION_14
}

tasks {
	compileKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}
	compileTestKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}
}
