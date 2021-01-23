# First stage: builds the app
FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Second stage: setup the app
FROM openjdk:8-jdk-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/mi-curriculum-api.jar
ENTRYPOINT ["java","-jar","/app/mi-curriculum-api.jar","-Dspring.actives.profile=prod"]