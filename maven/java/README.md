# Maven Java Template

Template for a Java development using Maven 3.2.x and Java 11+.

## Features

- [Maven Wrapper](https://maven.apache.org/wrapper/)
- [WildFly](https://github.com/wildfly/wildfly-checkstyle-config) [checkstyle](https://checkstyle.sourceforge.io/) configuration
- [Maven Enforcer Plugin](https://maven.apache.org/enforcer/maven-enforcer-plugin/) with rules enforcing 
  - secure repositories over HTTPS
  - Java >=11 
  - Maven >= 3.2.5
- [Maven License Plugin](https://mycila.carbou.me/license-maven-plugin/)
- [Maven Formatter Plugin](https://code.revelc.net/formatter-maven-plugin/)
- [Maven ImpSort Plugin](https://code.revelc.net/impsort-maven-plugin/)

## Get Started

1. Clone or copy repo
2. Adjust Maven coordinates in `pom.xml`
3. Adjust `repo.scm.connection` and `repo.scm.url` in `pom.xml`
6. Adjust or remove unnecessary plugins / configuration in `pom.xml`
5. Add dependencies, code and tests
