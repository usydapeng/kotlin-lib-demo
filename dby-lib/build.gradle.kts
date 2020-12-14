import java.util.*

plugins {
  kotlin("jvm") version "1.4.10"
  `maven-publish`
  signing
  id("org.jetbrains.dokka") version "1.4.10.2"
  id("com.jfrog.bintray") version "1.8.5"
  id("fr.coppernic.versioning") version "3.2.1"
}

val groupId = "com.dby.jweb"

group = groupId
version = "0.0.1"

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

val publicationName = "kotlinLib"
val repoWebsiteUrl = "https://zunpeng.org/kotlin-lib-demo"
val repoOrganization = "PC"
// val artifactId = project.name
val artifactName = "kotlin-lib"
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val githubUrl = "https://github.com/usydapeng/kotlin-lib-demo"
val githubRepository = "usydapeng/kotlin-lib-demo"
val githubReadme = "README.md"

val pomUrl = githubUrl
val pomScmUrl = githubUrl
val pomIssueUrl = "https://github.com/usydapeng/kotlin-lib-demo/issues"
val pomDesc = "kotlin lib demo description: $githubUrl"
val pomScmConnection = "scm:git:git://github.com/usydapeng/kotlin-lib-demo.git"
val pomScmDevConnection = "scm:git:ssh://github.com/usydapeng/kotlin-lib-demo.git"

val pomLicenseName = "The Apache Software License, Version 2.0"
val pomLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
val pomLicenseDist = "repo"

val pomDevelopId = "usydapeng"
val pomDevelopName = "Wang Zunpeng"
val pomDevelopEmail = "usydapeng@gmail.com"

val dokkaJavadocJar by tasks.creating(Jar::class) {
  group = JavaBasePlugin.DOCUMENTATION_GROUP
  description = pomDesc
  dependsOn(tasks.dokkaJavadoc)
  from(tasks.dokkaJavadoc)
  archiveClassifier.set("javadoc")
}

val sourcesJar by tasks.creating(Jar::class) {
  archiveClassifier.set("sources")
  from(sourceSets.getByName("main").allSource)
}

publishing {
  publications {
    create<MavenPublication>(publicationName) {
      artifactId = artifactName
      groupId = artifactGroup
      version = artifactVersion
      from(components["java"])
      artifact(sourcesJar)
      artifact(dokkaJavadocJar)
      pom {
        name.set(artifactName)
        description.set(pomDesc)
        url.set(pomUrl)
        properties.set(mapOf(
          "myProp" to "value",
          "prop.with.dots" to "anotherValue"
        ))
        licenses {
          license {
            name.set(pomLicenseName)
            url.set(pomLicenseUrl)
            distribution.set(pomLicenseDist)
          }
        }
        developers {
          developer {
            id.set(pomDevelopId)
            name.set(pomDevelopName)
            email.set(pomDevelopEmail)
          }
        }
        scm {
          connection.set(pomScmConnection)
          developerConnection.set(pomScmDevConnection)
          url.set(pomScmUrl)
        }
      }
    }
  }
  // publish to github packages
  if (System.getenv("GITHUB_TOKEN") != null) {
    repositories {
      maven {
        name = artifactName
        url = uri(githubUrl)
        credentials {
          username = System.getenv("GITHUB_ACTOR")
          password = System.getenv("GITHUB_TOKEN")
          println("username: $username, password: $password")
        }
      }
    }
  }
}

bintray {
  user = System.getenv("BINTRAY_USER")
  key = System.getenv("BINTRAY_KEY")
  println("jcenter user: $user, key: $key")
  publish = true
  setPublications(publicationName)
  pkg.apply {
    repo = "maven"
    name = artifactName
    // 如果是组织，例如: PC/maven/kotlin-lib
    // userOrg = repoOrganization
    // Error: org.gradle.api.GradleException: Could not create package, message:User is not "authorized" on GitHub 403
    // githubRepo = githubRepository
    // githubReleaseNotesFile = githubReadme
    vcsUrl = pomScmUrl
    description = pomDesc
    websiteUrl = repoWebsiteUrl
    issueTrackerUrl = pomIssueUrl

    setLicenses("Apache-2.0")
    setLabels("kotlin", "library", "dokka")

    version.apply {
      name = artifactVersion
      desc = pomDesc
      released = Date().toString()
      vcsTag = artifactVersion
    }
  }
}

//signing {
//  sign(publishing.publications["mavenJava"])
//}

tasks.dokkaHtml.configure {
  outputDirectory.set(buildDir.resolve("dokka"))
}
