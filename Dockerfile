FROM spring-maven-jdk11-base
RUN apk update && apk add maven
COPY . /src
WORKDIR /src
CMD mvn org.springframework.boot:spring-boot-maven-plugin:run
