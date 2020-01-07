# org.openwms.services
A typical service discovery server like shown in all SpringBoot examples

![Build status][ci-image]

# Build and Run locally

```
$ ./mvnw package
$ java -jar target/openwms-services.jar 
```

# Release

```
$ mvn clean deploy -Prelease,gpg
```

[ci-image]: https://img.shields.io/jenkins/s/http/openwms.mooo.com:8080/view/All/job/Spring%20Labs/job/org.openwms.services.svg
