plugins {
  java
  `maven`
}

group = "com.dby.jweb"
version = "0.0.1"

java {
  sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

repositories {
  mavenLocal()
  maven("https://maven.aliyun.com/repository/public/")
  mavenCentral()
}

dependencies {
  implementation("com2.dby.jweb:jweb-lib:0.0.1-1")
  testImplementation("junit", "junit", "4.12")
}

tasks.jar {
  manifest {
    attributes(mapOf("Main-Class" to "com.dby.jweb.app.HelloWorld"))
  }
  manifestContentCharset = "utf-8"
}
