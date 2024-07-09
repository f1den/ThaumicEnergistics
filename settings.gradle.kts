pluginManagement {
    repositories {
        maven {
            // RetroFuturaGradle
            name = "GTNH Maven"
            url = uri("http://jenkins.usrv.eu:8081/nexus/content/groups/public/")
            isAllowInsecureProtocol = true
            mavenContent {
                includeGroup("com.gtnewhorizons")
                includeGroup("com.gtnewhorizons.retrofuturagradle")
            }
        }
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }
}

plugins {
//     Automatic toolchain provisioning g
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

// Due to an IntelliJ bug, this has to be done
// rootProject.name = archives_base_name
rootProject.name = rootProject.projectDir.getName()
