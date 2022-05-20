FROM mytestbi:latest

COPY src /tmp/src/
COPY pom.xml /tmp/

WORKDIR /tmp/
RUN mkdir allure-results
CMD mvn clean test