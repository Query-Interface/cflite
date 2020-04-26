FROM maven:3.6.3 AS build

WORKDIR /app
# copy pom file
COPY pom.xml .
# install dependencies and plugins
RUN mvn dependency:go-offline
# copy source code
COPY src src/
# build and package application
RUN mvn package -DskipTests

# ---------------------

FROM alpine:latest as base
ENV VERSION="v0.10.0"

RUN apk update && \
	apk upgrade && \
	apk add wget && \
	apk add tar

RUN apk add docker

RUN apk add openrc && \
	rc-update add docker boot

RUN wget https://github.com/buildpacks/pack/releases/download/${VERSION}/pack-${VERSION}-linux.tgz  && \
	tar xvf pack-v0.10.0-linux.tgz && \
	rm pack-v0.10.0-linux.tgz && \
	mv ./pack /usr/bin

RUN apk --no-cache add openjdk11

COPY --from=build /app/target/buildpack-service-0.0.1-SNAPSHOT.jar /app/buildpack-service.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/buildpack-service.jar"]
