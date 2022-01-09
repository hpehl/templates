# Kotlin/JS App Template

Template for Kotlin/JS only (no multiplatform!) app development using Gradle Kotlin DSL.

## Features

- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)
- [Gradle version catalogs](https://docs.gradle.org/current/userguide/platforms.html)
- [ktlint](https://ktlint.github.io/) and [detekt](https://detekt.github.io/detekt/) setup
- Kotlin and NPM dependencies (Kotlin HTML DSL and Normalize.css)

## Get Started

1. Clone or copy repo
2. Adjust `rootProject.name` in `settings.gradle.kts`
3. Adjust `group` and `version` in `build.gradle.kts`
5. Adjust dependencies in `gradle/libs.versions.toml` and `build.gradle.kts`
6. Adjust or remove unnecessary plugins / configuration in `build.gradle.kts`
7. Add code and tests
