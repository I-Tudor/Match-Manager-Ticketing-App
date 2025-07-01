import com.google.protobuf.gradle.id

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.google.protobuf") version "0.9.4"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

javafx {
    version = "23.0.2"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.swing", "javafx.web", "javafx.graphics", "javafx.media")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javafxVersion = "23.0.2"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.slf4j:slf4j-api:2.0.0-alpha1")
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("mysql:mysql-connector-java:8.0.32")
    implementation("org.hibernate.orm:hibernate-core:6.4.0.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.openjfx:javafx-fxml:$javafxVersion")
    implementation("org.openjfx:javafx-graphics:$javafxVersion")
    implementation("org.openjfx:javafx-base:$javafxVersion")
    implementation("io.grpc:grpc-netty-shaded:1.64.0") {
        exclude("com.google.code.findbugs", "jsr305")
    }
    implementation("io.grpc:grpc-protobuf:1.64.0") {
        exclude(group = "com.google.code.findbugs", module = "jsr305")
    }
    implementation("io.grpc:grpc-stub:1.64.0") {
        exclude(group = "com.google.code.findbugs", module = "jsr305")
    }
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    implementation("org.springframework.boot:spring-boot-starter") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }

    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("com.fasterxml.jackson.core:jackson-databind")
}
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "ch.qos.logback") {
            useVersion("1.4.14")
            because("Fix NoSuchMethodError caused by incompatible Logback version")
        }
    }
    resolutionStrategy.eachDependency {
        if (requested.group == "org.slf4j") {
            useVersion("2.0.13")
            because("Fix NoClassDefFoundError by aligning SLF4J version to 2.x")
        }
    }
}

application {
    mainClass.set("org.example.GUI.MainApp")
}

tasks.withType<JavaCompile>().configureEach {
    modularity.inferModulePath = true
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.2"
    }
    plugins {
        id("grpc") {
            path = "/opt/homebrew/bin/protoc-gen-grpc-java"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
        java {
            srcDirs("build/generated/source/proto/main/java", "build/generated/source/proto/main/grpc")
        }
    }
}

tasks.withType<JavaExec> {
    jvmArgs = listOf(
        "--module-path",
        configurations.runtimeClasspath.get().asPath,
        "--add-modules", "javafx.controls,javafx.fxml"
    )
}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


