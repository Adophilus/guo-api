FROM openjdk:22-jdk

WORKDIR /app

COPY build/libs/library-0.0.1-SNAPSHOT.jar app.jar

CMD java $JAVA_OPTS -jar app.jar -server.port=$PORT $JAR_OPTS
