plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.4'
    id 'io.spring.dependency-management' version '1.0.14.RELEASE'
}
repositories {
    mavenCentral()
}

dependencies{
    implementation 'org.springframework.boot:spring-boot-starter'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}

test {
    useJUnitPlatform()
}

tasks.jar{
    manifest.attributes["Main-Class"] = 'ru.netology.operations.Main'
}
