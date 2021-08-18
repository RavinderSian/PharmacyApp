FROM arm32v7/adoptopenjdk:11-jre-hotspot
COPY . /src
WORKDIR src/main/java/com/personal/pharmacy/
RUN java src/main/java/com/personal/pharmacy/PharmacyApplication.java
CMD ["java", "PharmacyApplication"]