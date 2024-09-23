# io.openleap.registry
A typical service discovery server used to register microservices and find each other, based on Netflix Eureka.

![Build status][ci-image]

# Build and Run locally
```
$ ./mvnw package
$ java -jar target/openleap-registry-exec.jar 
```

# Release
```
$ ./mvnw release:prepare
$ ./mvnw release:perform
```

# Resources
[![Build status](https://github.com/openleap-io/io.openleap.services/actions/workflows/master-build.yml/badge.svg)](https://github.com/spring-labs/org.openwms.services/actions/workflows/master-build.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Maven central](https://img.shields.io/maven-central/v/io.openleap/io.openleap.registry)](https://search.maven.org/search?q=a:io.openleap/io.openleap.registry)
[![Docker pulls](https://img.shields.io/docker/pulls/openleap/openleap-registry)](https://hub.docker.com/r/openleap/openleap-registry)

