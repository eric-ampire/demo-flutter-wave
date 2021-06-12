dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    maven(url = "https://jitpack.io")
    mavenCentral()
    jcenter() // Warning: this repository is going to shut down soon
  }
}
rootProject.name = "demo-flutter-wave"
include(":app")
