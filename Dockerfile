FROM maven:3.8.3-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml /app
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src /app/src
RUN mvn package
COPY . ./

FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
COPY --from=builder /app/target ./
CMD java -jar delivuniv-0.0.1-SNAPSHOT.jar