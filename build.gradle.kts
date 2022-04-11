import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	// https://mvnrepository.com/artifact/ch.vorburger.mariaDB4j/mariaDB4j
	testImplementation("ch.vorburger.mariaDB4j:mariaDB4j:2.5.3")
	// https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
	implementation("org.mariadb.jdbc:mariadb-java-client:3.0.4")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// https://mvnrepository.com/artifact/org.flywaydb/flyway-core
	implementation("org.flywaydb:flyway-core:5.2.4")
	// https://mvnrepository.com/artifact/ch.vorburger.mariaDB4j/mariaDB4j-core
	implementation("ch.vorburger.mariaDB4j:mariaDB4j-core:2.5.3")
	// https://mvnrepository.com/artifact/ch.vorburger.mariaDB4j/mariaDB4j-db-win32
	implementation("ch.vorburger.mariaDB4j:mariaDB4j-db-win32:10.2.11")
	// https://mvnrepository.com/artifact/mysql/mysql-connector-java
	implementation("mysql:mysql-connector-java:8.0.28")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis:2.6.6")

	compileOnly("org.projectlombok:lombok")

	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.12.3")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
