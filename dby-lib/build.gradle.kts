plugins {
  kotlin("jvm") version "1.4.10"
  `maven-publish`
  `java-library`
  signing
}

group = "com.dby.jweb"
version = "0.0.7"

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
}

dependencies {
  implementation(kotlin("stdlib"))
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
        name.set("My Library")
        description.set("A concise description of my library")
        url.set("http://www.example.com/library")
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
            id.set("johnd")
            name.set("John Doe")
            email.set("john.doe@example.com")
          }
        }
        scm {
          connection.set("scm:git:git://example.com/my-library.git")
          developerConnection.set("scm:git:ssh://example.com/my-library.git")
          url.set("http://example.com/my-library/")
        }
      }
    }
  }
  repositories {
    maven {
      // change URLs to point to your repos, e.g. http://my.org/repo
      val releasesRepoUrl = uri("$buildDir/repos/releases")
      val snapshotsRepoUrl = uri("$buildDir/repos/snapshots")
      url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
    }
  }
}

//signing {
//  sign(publishing.publications["mavenJava"])
//}

tasks.javadoc {
  if (JavaVersion.current().isJava9Compatible) {
    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
  }
}
