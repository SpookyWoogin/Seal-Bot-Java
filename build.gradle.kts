plugins {
    id("java")
}

group = "self.lol"
version = "1.0-SNAPSHOT"

repositories {
    maven ("https://oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}


dependencies {
    implementation ("com.discord4j:discord4j-core:3.2.3-SNAPSHOT")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}