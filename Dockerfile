FROM openjdk:8
COPY build/libs/app.jar /app.jar
EXPOSE 8888
CMD java -jar /app.jar