object Versions {
    const val kotlin = "1.4.10"
    const val androidGradleBuild = "4.1.1"
    const val androidxCore = "1.3.2"
    const val appcompat = "1.2.0"
    const val material = "1.2.1"
    const val lifecycle = "2.2.0"
    const val constraintLayout = "2.0.4"
    const val navigation = "2.3.1"
    const val retrofit = "2.9.0"
    const val koin = "2.2.0"
    //Test
    const val junit = "4+"
    const val androidxTesting = "1.1.2"
    const val espresso = "3.3.0"
    const val mockk = "1.10.2"
    const val coroutinesCore = "1.4.1"
    const val archCore = "2.1.0"
}
object Dependencies {
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidGradleBuildPlugin = "com.android.tools.build:gradle:${Versions.androidGradleBuild}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val viewModelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTesting = "androidx.test.ext:junit:${Versions.androidxTesting}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val koin = "org.koin:koin-test:${Versions.koin}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutinesCoreSupport = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesCore}"
    const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
}