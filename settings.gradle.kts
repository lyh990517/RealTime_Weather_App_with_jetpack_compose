pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://naver.jfrog.io/artifactory/maven/")
    }
}

rootProject.name = "RealTime Weather App"
include(":app")
include(":library")
include(":feature")
include(":library:network")
include(":library:network-contract")
include(":core")
include(":ui_component")
include(":feature:map")
include(":MapUtil")
include(":weather-util")
include(":feature:detail")
