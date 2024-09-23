# OpenWMS.org Registry Service
A typical discovery service used to register microservice instances that need to find each other, based on Netflix Eureka.

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
[![Build status](https://github.com/openleap-io/io.openleap.registry/actions/workflows/master-build.yml/badge.svg)](https://github.com/openleap.io/io.openleap.registry/actions/workflows/master-build.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Maven central](https://img.shields.io/maven-central/v/io.openleap/io.openleap.registry)](https://search.maven.org/search?q=a:io.openleap.registry)
[![Docker pulls](https://img.shields.io/docker/pulls/openleap/io.openleap.registry)](https://hub.docker.com/r/openleap/io.openleap.registry)
