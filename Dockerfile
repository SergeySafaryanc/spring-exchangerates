FROM gradle:7-jdk11-alpine AS build

WORKDIR /code/gradle/src

COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon


FROM openjdk:11-jre
EXPOSE 9092

RUN mkdir /app

ENV OPEN_EXCHANGE_RATE_APP_KEY ""
ENV GIPHY_API_KEY ""
ENV LOG_NAME "app.log"

COPY --from=build /code/gradle/src/build/libs/*-RELEASE.jar ./app.jar
ENTRYPOINT ["java", "-jar", "-Dlogging.file.name=./log/${LOG_NAME}", "-Dopenexchangerate.appId=${OPEN_EXCHANGE_RATE_APP_KEY}", "-Dgiphy.apiKey=${GIPHY_API_KEY}", "app.jar"]