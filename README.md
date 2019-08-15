# homebank-server

[![Build Status](https://travis-ci.com/cyosp/homebank-server.svg?branch=master)](https://travis-ci.com/cyosp/homebank-server)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=homebank-server&metric=alert_status)](https://sonarcloud.io/dashboard?id=homebank-server)
[![BSD-3-Clause License](https://img.shields.io/badge/license-BSD--3--Clause-428F7E.svg)](https://tldrlegal.com/license/bsd-3-clause-license-%28revised%29)

A server based on HomeBank XML file

## Build

### Bootable JAR

homebank-server can be built into a single bootable JAR with:

`./gradlew bootJar`

Bootable JAR is then in directory: `build/libs`

### Docker image

homebank-server can also be built into a Docker image

In that case build the bootable JAR and run after:

`./gradlew docker`

Built Docker image name: `cyosp/homebank-server`

## Run

### Bootable JAR

Put a `homebank.xhb` file at project level and run:

`java -jar build/libs/homebank-server-*.jar`

### Docker image

Replace:

 * `/path/to/your/homebank/file.xhb` with your own Homebank file path
 * `2.0.0-SNAPSHOT` with homebank-server version expected

And run:

`docker run -v /path/to/your/homebank/file.xhb:/homebank.xhb -p 8080:8080 cyosp/homebank-server:2.0.0-SNAPSHOT`

## IntelliJ configuration

Steps to follow:
 * Checkout the repository and go into project folder
 * Run: `./gradlew idea`
 * Start IntelliJ and open project folder
 * Click: `Import Gradle Project` when IntelliJ displays information event: `IntelliJ IDEA found a Gradle build script`
 