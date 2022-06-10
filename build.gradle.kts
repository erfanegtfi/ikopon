buildscript {
    repositories {
        maven(url =  uri(Repositories.MAVEN_GOOGLE))
        maven(url =  uri(Repositories.MAVEN_JITPACK))
        maven (url = "https://plugins.gradle.org/m2/" )
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(GradlePlugin.GRADLE_BUILD_TOOLS)
        classpath(GradlePlugin.KOTLIN_GRADLE_PLUGIN)
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}