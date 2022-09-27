FROM openjdk:11 as build
WORKDIR /workspace/app
COPY . /workspace/app
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

FROM openjdk:11
COPY --from=build /workspace/app/build/libs/*-SNAPSHOT.jar /app.jar
ENV TZ="Asia/Ho_Chi_Minh"
ENTRYPOINT ["java","-jar","/app.jar"]