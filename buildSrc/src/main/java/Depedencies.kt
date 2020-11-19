object Versions {
    val kotlin = "1.4.10"
    val androidGradleBuild = "4.1.1"
    val androidxCore = "1.3.2"
    val appcompat = "1.2.0"
    val material = "1.2.1"
    val constraintLayout = "2.0.4"
    val navigation = "2.3.1"
    val retrofit = "1.6.0"
    //Test
    val junit = "4+"
    val androidxTesting = "1.1.2"
    val espresso = "3.3.0"
}
object Dependencies {
    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val androidGradleBuildPlugin = "com.android.tools.build:gradle:${Versions.androidGradleBuild}"
    val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}

object TestDependencies {
    val junit = "junit:junit:${Versions.junit}"
    val androidxTesting = "androidx.test.ext:junit:${Versions.androidxTesting}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}