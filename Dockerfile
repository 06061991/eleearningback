FROM openjdk:17-bullseye
RUN apt-get update && apt-get install -y maven
WORKDIR /code/asslambackend
COPY .  .
RUN rm -rf target
RUN mvn clean package -DskipTests --no-transfer-progress -B



RUN mv target/*.jar  /code/app.jar
RUN rm -rf /code/asslambackend   /root/.m2  /root/.cache /tmp/* /var/tmp/*

# Définir le répertoire de travail
EXPOSE 8094
ENTRYPOINT ["java", "-jar", "/code/app.jar"]
