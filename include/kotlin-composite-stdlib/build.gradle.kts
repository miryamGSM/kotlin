plugins {
    kotlin("jvm")
}

val sources by configurations.creating

dependencies {
    sources(project(":kotlin-stdlib-common", configuration = "sources"))
}

val jar: Jar by tasks
jar.apply {
    dependsOn(sources)
    baseName = "kotlin-stdlib-common"
    classifier = "sources"

    from(zipTree(sources.singleFile))
}
