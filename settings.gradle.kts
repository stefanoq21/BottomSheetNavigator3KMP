import java.util.Properties

include(":material3-navigation-kmp")


rootProject.name = "BottomSheetNavigator3KMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/stefanoq21/BottomSheetNavigator3")
            credentials {
                val properties = Properties()
                properties.load(file("local.properties").reader())
                username = properties.getProperty("githubUserName") as String
                password = properties.getProperty("githubToken") as String
            }
        }
    }
}

include(":composeApp")