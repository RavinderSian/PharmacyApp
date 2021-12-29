FROM spring-maven-jdk11-base
RUN apk update && apk add maven
COPY . /src
WORKDIR /src
RUN mvn package
CMD mvn spring-boot:run
