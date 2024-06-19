pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EMAir"
include(":app")
include(":feature:home")
include(":core:ui")
include(":feature:hotels")
include(":feature:places")
include(":feature:subscriptions")
include(":feature:profile")
include(":core:network")
include(":core:data")
include(":feature:search")
include(":feature:offers")
