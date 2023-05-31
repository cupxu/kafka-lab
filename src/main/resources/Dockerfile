FROM openjdk:8-jdk-alpine

RUN mkdir /app
WORKDIR /app

COPY kafka-consumer.jar /app/kafka-consumer.jar

EXPOSE 8080

CMD ["java", "-jar", "kafka-consumer.jar"]