object AndroidConfig {
    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
    const val BUILD_TOOLS_VERSION = "30.0.2"

    const val ID = "com.ikopon.ikopon"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
}


interface BuildType {
    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
        const val STAGING = "staging"
    }

    val isMinifyEnabled: Boolean
    val isDebuggable: Boolean
    val versionNameSuffix: String
}

object BuildTypeDebug: BuildType {
    const val APPLICATION_ID_SUFFIX = ".dev"
    override val isMinifyEnabled = false
    override val isDebuggable = true
    override val versionNameSuffix = "-dev"
}

object BuildTypeStage: BuildType {
    const val APPLICATION_ID_SUFFIX = ".stage"
    override val isMinifyEnabled = false
    override val isDebuggable = true
    override val versionNameSuffix = "-stage"
}

object BuildTypeRelease: BuildType {
    override val isMinifyEnabled = true
    override val isDebuggable = false
    override val versionNameSuffix = ""
}

