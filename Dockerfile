FROM maven:3.8.3-openjdk-17
COPY src home/apiframework/src
COPY pom.xml home/apiframework/pom.xml
WORKDIR home/apiframework
ENTRYPOINT mvn clean verify