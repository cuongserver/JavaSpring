#FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10 AS TEMP_BUILD_IMAGE
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY build.gradle settings.gradle gradlew $APP_HOME
#COPY gradle $APP_HOME/gradle
#RUN ./gradlew build --status || return 0
#COPY . .
#RUN ./gradlew build --status
#
#FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10
#ENV ARTIFACT_NAME=demo.jar
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
#EXPOSE 8080
#CMD ["java","-jar",$ARTIFACT_NAME]

#FROM gradle:6.6.1-jdk14 AS TEMP_BUILD_IMAGE
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY build.gradle settings.gradle $APP_HOME
#
#COPY gradle $APP_HOME/gradle
#COPY --chown=gradle:gradle . /home/gradle/src
#USER root
#RUN chown -R gradle /home/gradle/src
#
###ADD gradle $APP_HOME/gradle
#ADD src $APP_HOME/src
#RUN gradle build
##COPY . .
##RUN ./gradlew build --status
#
#FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10
#ENV ARTIFACT_NAME=demo-0.0.1-SNAPSHOT.jar
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
#COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
#EXPOSE 8090
#ENTRYPOINT ["java","-jar","./demo-0.0.1-SNAPSHOT.jar"]

FROM gradle:6.6.1-jdk14 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.6_10
EXPOSE 8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","./spring-boot-application.jar"]