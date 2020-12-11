plugins {
  kotlin("jvm") version "1.4.10"
  `maven-publish`
  `java-library`
  signing
  id("org.jetbrains.dokka") version "1.4.10.2"
}

group = "com.dby.jweb"
version = "0.0.1"

java {
  withJavadocJar()
  withSourcesJar()
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  api(kotlin("stdlib"))
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      artifactId = "jweb-lib"
      from(components["java"])
      versionMapping {
        usage("java-api") {
          fromResolutionOf("runtimeClasspath")
        }
        usage("java-runtime") {
          fromResolutionResult()
        }
      }
      pom {
        name.set("kotlin library")
        description.set("A library of kotlin")
        url.set("https://github.com/usydapeng/kotlin-lib-demo")
        properties.set(mapOf(
          "myProp" to "value",
          "prop.with.dots" to "anotherValue"
        ))
        licenses {
          license {
            name.set("The Apache License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
          developer {
            id.set("usydapeng")
            name.set("Wang Zunpeng")
            email.set("usydapeng@gmail.com")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/usydapeng/kotlin-lib-demo.git")
          developerConnection.set("scm:git@github.com:usydapeng/kotlin-lib-demo.git")
          url.set("https://github.com/usydapeng/kotlin-lib-demo")
        }
      }
    }
  }
  repositories {
    maven {
      name = "kotlin-lib-demo"
      url = uri("https://maven.pkg.github.com/usydapeng/kotlin-lib-demo")
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
        println("username: $username, password: $password")
      }
    }
  }
}

//signing {
//  sign(publishing.publications["mavenJava"])
//}

tasks.dokkaHtml.configure {
  outputDirectory.set(buildDir.resolve("dokka"))
}
