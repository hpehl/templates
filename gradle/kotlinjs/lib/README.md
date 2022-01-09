# Kotlin/JS Lib Template

Template for Kotlin/JS only (no multiplatform!) lib development using Gradle Kotlin DSL.

## Features

- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)
- [Gradle version catalogs](https://docs.gradle.org/current/userguide/platforms.html)
- [ktlint](https://ktlint.github.io/), [detekt](https://detekt.github.io/detekt/) and [dokka](https://kotlin.github.io/dokka/1.6.10/) setup
- [kotest](https://kotest.io/) integration
- Ready to be [published](https://h4pehl.medium.com/publish-your-gradle-artifacts-to-maven-central-f74a0af085b1) on Maven Central

## Get Started

1. Clone or copy repo
2. Adjust `rootProject.name` in `settings.gradle.kts`
3. Adjust `group` and `version` in `build.gradle.kts`
4. Adjust `Meta` object in `build.gradle.kts`
5. Adjust dependencies in `gradle/libs.versions.toml` and `build.gradle.kts`
6. Adjust or remove unnecessary plugins / configuration in `build.gradle.kts`
7. Add code and tests
