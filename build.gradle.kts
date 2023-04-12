import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.9"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("org.sonarqube") version "4.0.0.2929"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("kapt") version "1.6.21"
	id("org.jetbrains.dokka") version "1.6.21"
	id("jacoco")
	id("org.owasp.dependencycheck") version "8.1.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencyManagement {
	imports {
		mavenBom("io.mongock:mongock-bom:5.2.2")
	}
	dependencies {
		dependency("io.springfox:springfox-boot-starter:3.0.0")
		dependency("org.mockito:mockito-junit-jupiter:5.0.0")
		dependency("org.mockito:mockito-core:5.1.1")
		dependency("org.mockito.kotlin:mockito-kotlin:4.1.0")
		dependency("org.assertj:assertj-core:3.24.2")
	}
}

dependencies {
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	kapt("org.springframework.boot:spring-boot-configuration-processor")

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	//implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.mongock:mongock-springboot")
	implementation("io.mongock:mongodb-springdata-v3-driver")


	// Swagger
	//implementation("io.springfox:springfox-boot-starter")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.mockito.kotlin:mockito-kotlin")
	testImplementation("org.assertj:assertj-core")
}
tasks.withType<JavaCompile> {
	val compilerArgs = options.compilerArgs
	compilerArgs.add("-parameters")
	compilerArgs.add("-Xlint:all")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-java-parameters")
		jvmTarget = JavaVersion.VERSION_17.toString()
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named("compileJava") {
	inputs.files(tasks.named("processResources"))
}

jacoco {
	toolVersion = "0.8.8"
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report
	reports {
		html.required.set(true)
		xml.required.set(true)
	}
}

