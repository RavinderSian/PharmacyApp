FROM openjdk:18-ea-jdk
COPY . /src
WORKDIR /src/main/java/com/personal/pharmacy/
RUN javac PharmacyApplication.java
CMD ["java", "PharmacyApplication"]