FROM openjdk:18-jdk

WORKDIR /app

COPY . .

CMD apt update -y && apt install xargs -y && ./gradlew build && java $JAVA_OPTS -jar build/libs/library-0.0.1-SNAPSHOT.jar -server.port=$PORT $JAR_OPTS
