FROM arm32v7/adoptopenjdk:11-jre-hotspot
COPY . /src
WORKDIR /src/main/java/com/personal/pharmacy/
RUN sudo apt install default-jdk
RUN javac PharmacyApplication.java
CMD ["java", "PharmacyApplication"]