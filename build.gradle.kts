import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
	id("org.openapi.generator") version "7.2.0"

	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.liferay"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["kotestVersion"] = "5.8.0"
extra["openAPIVersion"] = "1.7.0"
extra["coroutinesCoreVersion"] = "1.8.0-RC2"
extra["springCloudVersion"] = "2023.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework:spring-tx")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-configuration-processor")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")

	// OpenAPI
	implementation("org.springdoc:springdoc-openapi-kotlin:${property("openAPIVersion")}")
	implementation("org.springdoc:springdoc-openapi-webflux-ui:${property("openAPIVersion")}")

	// Test
	testImplementation("io.kotest:kotest-runner-junit5:${property("kotestVersion")}")
	testImplementation("io.kotest:kotest-assertions-core:${property("kotestVersion")}")
	testImplementation("io.kotest:kotest-property:${property("kotestVersion")}")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${property("coroutinesCoreVersion")}")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")

	// Faker
	implementation("net.datafaker:datafaker:1.8.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
		csv.required.set(false)
	}
}