FROM adoptopenjdk/openjdk11-jre-hotspot
COPY . /src
WORKDIR /src/main/java/com/personal/pharmacy/
RUN java PharmacyApplication.java
CMD ["java", "PharmacyApplication"]