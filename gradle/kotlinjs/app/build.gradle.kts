group = "org.acme"
version = "0.0.1"

// ------------------------------------------------------ plugins

// https://youtrack.jetbrains.com/issue/KTIJ-19369#focus=Comments-27-5181027.0-0
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.js)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ktlintIdea)
    alias(libs.plugins.detekt)
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
    implementation(libs.kotlinx.html)
    implementation(npm("normalize.css", libs.versions.normalizeCss.get()))
}

// ------------------------------------------------------ kotlin/js

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}
