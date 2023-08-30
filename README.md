## Fetch Code Challenge
This repository contains code challenge from Fetch, created by Mario Valdez.

## Description

This application works like a details application to show some lists.


## Endpoints used
- The site used for this project to retrieve data from the server is:
  - [Fetch EndPoint](https://fetch-hiring.s3.amazonaws.com/hiring.json)

## Permissions of the App
- Internet

## Architecture
The application implements a [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), with the aim of:
- **Independency of frameworks:** The architecture doesn't depend on the existence of some library of feature laden software.
- **Testability:** The business rules can be tested without the UI, Database, Web Server, or any other external element.
- **Independency of UI:** The UI can change easily, without changing the rest of the system, e.g. Jetpack Compose.
- **Independency of Database:** The business rules are not bound to the database.
- **Independency of any external agency:** The business rules simply don't know anything at all about the outside world.

The following diagram describes in depth the concrete implementation of our architecture:
![Architecture](https://github.com/MarioValdezDev/fetch-code-challenge/blob/main/images/architecture.png)

## Tech Stack
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
    - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- [Ktlint](https://ktlint.github.io/) - Kotlin linter for format standardization and code inspection.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Android Studio](https://developer.android.com/studio) - IDE version Android Studio Flamingo | 2022.2.1 patch 2
- [Jetpack Compose](https://developer.android.com/jetpack?authuser=1&hl=es-419) - for UI
- [Ktlint](https://ktlint.github.io/) - Kotlin linter for format standardization and code inspection `./gradlew ktlintFormat`


## Git Branch Strategy
We used [Trunk-based development (TBD)](https://launchdarkly.com/blog/introduction-to-trunk-based-development/) as the branching strategy.

The idea of this strategy is that all developers integrate their changes directly to a shared trunk every day (in our case, our `main` branch), a shared trunk that is always in a releasable state. No matter what a developer might do on their local repository, at least once each day, they must integrate their code. This practice forces each developer to regularly see and react to the changes being made by their teammates in version control, which drives collaboration around the quality and state of the codebase as a near-constant activity.

### Branch Naming
In order to have a great administration with our branches, we have established a convention that will allow us to identify them more quickly, the structure is as follows:

    type/username/short-description

Where
- `type`: The type of contribution you are about to make, we have at least 4.
    - `core`: Important changes to the project, an update in some fundamental part of the application, change in some configuration or addition of a specific library.
    - `feature`: Any new development that adds functionality to the project.
    - `fix`: The solution to a bug.
    - `release`: When bumping the `versionCode` and the `versionName` for the signature of a productive build.
- `username`: Your GitHub username.
- `short-description`: A brief description separated by `-` that help us to identify the objective of this branch.

**e.g.**

    feature/mariovaldezdev/add-new-feature
