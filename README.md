# Kotlin Scripts Commons Helper
[![Build Status](https://travis-ci.org/jasoet/scripts-commons.svg?branch=master)](https://travis-ci.org/jasoet/scripts-commons)
[![codecov](https://codecov.io/gh/jasoet/scripts-commons/branch/master/graph/badge.svg)](https://codecov.io/gh/jasoet/scripts-commons)
[![JCenter](https://api.bintray.com/packages/jasoet/fun/scripts-commons/images/download.svg)](https://bintray.com/jasoet/fun/scripts-commons/_latestVersion)

Kotlin Helper to manage several cloud related config. Will be useful for configuring cloud credentials inside docker image.

## Features
- Get Properties from OS Environment Variable and Java System Properties
- ANSI Colored String for Console
- Execute command and redirect output to file
- AWS Credentials
- Chef Client Config
- Google Cloud
- Ssh Config 

## Gradle

### Add Maven Central or JCenter repository
```groovy
repositories {
    jcenter()
}
```


### Add dependency 
```groovy
compile 'id.jasoet:scripts-commons:1.0.0'
```

## Maven
### Add dependency
```xml
<dependency>
  <groupId>id.jasoet</groupId>
  <artifactId>scripts-commons</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
