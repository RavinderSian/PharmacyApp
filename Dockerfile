FROM openjdk:11
COPY .
WORKDIR /src/main/java/com/personal/pharmacy/
RUN javac PharmacyApplication.java
CMD ["java", "PharmacyApplication"]