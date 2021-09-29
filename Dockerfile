FROM openjdk:8-jdk-alpine
MAINTAINER ernezcatovic@gmail.com

ARG JAR_FILE=target/craft-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar
COPY src/main/resources/db/ /app/db/
VOLUME /app/db
EXPOSE 8081

WORKDIR /app

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.ernez.craftapp.CraftAppApplication"]

#https://spring.io/guides/topicals/spring-boot-docker/

#docker build -t ernez/craftapp .

#docker run --rm -p 8080:8081 -v C:\myDev\craft-app\dbLocal\:/app/db -e JAVA_OPTS="-Xms512m -Xmx1024m" -d --name=craft ernez/craftapp
