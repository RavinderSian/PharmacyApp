FROM adoptopenjdk/openjdk11:ubi
COPY . /src
WORKDIR /src/main/java/com/personal/pharmacy/
RUN apt-get install openjdk-11-jdk
RUN java PharmacyApplication.java
CMD ["java", "PharmacyApplication"]