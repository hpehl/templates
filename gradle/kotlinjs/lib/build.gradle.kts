import org.jetbrains.dokka.Platform
import java.net.URL

group = "org.acme"
version = "0.0.1"

object Meta {
    const val desc = "Project description"
    const val license = "Apache-2.0"
    const val githubRepo = "acme/sample"
    const val release = "https://s01.oss.sonatype.org/service/local/"
    const val snapshot = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
}

// ------------------------------------------------------ plugins

// https://youtrack.jetbrains.com/issue/KTIJ-19369#focus=Comments-27-5181027.0-0
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.js)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ktlintIdea)
    alias(libs.plugins.detekt)
    alias(libs.plugins.nexusPublish)
    `maven-publish`
    signing
}

// ------------------------------------------------------ repositories

val repositories = arrayOf(
    "https://oss.sonatype.org/content/repositories/snapshots/",
    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
)

repositories {
    mavenLocal()
    mavenCentral()
    repositories.forEach { maven(it) }
}

// ------------------------------------------------------ dependencies

dependencies {
    testImplementation(libs.bundles.kotest)
}

// ------------------------------------------------------ kotlin/js

kotlin {
    js(IR) {
        explicitApi()
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
}

// ------------------------------------------------------ source & javadoc

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(kotlin.sourceSets.main.get().kotlin)
}

val javadocJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Javadoc JAR"
    archiveClassifier.set("javadoc")
    from(tasks.named("dokkaHtml"))
}

// ------------------------------------------------------ ktlint, detekt & dokka

tasks {
    dokkaHtml.configure {
        dokkaSourceSets {
            named("main") {
                includeNonPublic.set(false)
                noJdkLink.set(false)
                noStdlibLink.set(false)
                platform.set(Platform.js)
                skipEmptyPackages.set(true)
                sourceLink {
                    localDirectory.set(file("src/main/kotlin"))
                    remoteUrl.set(
                        URL("https://github.com/${Meta.githubRepo}/blob/main/src/main/kotlin/")
                    )
                    remoteLineSuffix.set("#L")
                }
                externalDocumentationLink {
                    url.set(URL("https://kotlin.github.io/kotlinx.coroutines/"))
                }
            }
        }
    }
}

// ------------------------------------------------------ sign & publish

signing {
    val signingKey = providers
        .environmentVariable("GPG_SIGNING_KEY")
        .forUseAtConfigurationTime()
    val signingPassphrase = providers
        .environmentVariable("GPG_SIGNING_PASSPHRASE")
        .forUseAtConfigurationTime()

    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
        val extension = extensions
            .getByName("publishing") as PublishingExtension
        sign(extension.publications)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set(project.name)
                description.set(Meta.desc)
                url.set("https://github.com/${Meta.githubRepo}")
                licenses {
                    license {
                        name.set(Meta.license)
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("hpehl")
                        name.set("Harald Pehl")
                        organization.set("Red Hat")
                        organizationUrl.set("https://developers.redhat.com/")
                    }
                }
                scm {
                    url.set("https://github.com/${Meta.githubRepo}.git")
                    connection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                    developerConnection.set("scm:git:git://github.com/#${Meta.githubRepo}.git")
                }
                issueManagement {
                    url.set("https://github.com/${Meta.githubRepo}/issues")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri(Meta.release))
            snapshotRepositoryUrl.set(uri(Meta.snapshot))
            val ossrhUsername = providers
                .environmentVariable("OSSRH_USERNAME")
                .forUseAtConfigurationTime()
            val ossrhPassword = providers
                .environmentVariable("OSSRH_PASSWORD")
                .forUseAtConfigurationTime()
            if (ossrhUsername.isPresent && ossrhPassword.isPresent) {
                username.set(ossrhUsername.get())
                password.set(ossrhPassword.get())
            }
        }
    }
}
