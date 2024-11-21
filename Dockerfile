FROM maven:3.9.9-amazoncorretto-11-debian
WORKDIR /app
COPY src src
COPY pom.xml .
CMD ["mvn", "clean", "test"]