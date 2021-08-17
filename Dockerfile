FROM arm32v7/adoptopenjdk:11-jre-hotspot
COPY . /src
WORKDIR /src/main/java/com/personal/pharmacy/
RUN apt-get install openjdk-11-jre
RUN javac PharmacyApplication.java
CMD ["java", "PharmacyApplication"]