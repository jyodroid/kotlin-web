plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
}
val kotlinJsVersion = "1.3.72"
val reactVersion = "16.13.1"

repositories {
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-js"))

    //React, React DOM + Wrappers
    implementation("org.jetbrains:kotlin-react:$reactVersion-pre.109-kotlin-$kotlinJsVersion")
    implementation("org.jetbrains:kotlin-react-dom:$reactVersion-pre.109-kotlin-$kotlinJsVersion")

    implementation(npm("react", reactVersion))
    implementation(npm("react-dom", reactVersion))

    //Kotlin Styled
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.109-kotlin-1.3.72")
    implementation(npm("styled-components"))
    implementation(npm("inline-style-prefixer"))

    //Video Player
    implementation(npm("react-player"))

    //Share Buttons
    implementation(npm("react-share"))

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
}

kotlin.target.browser { }