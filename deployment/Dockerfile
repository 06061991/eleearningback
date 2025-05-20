FROM openjdk:11-bullseye

# Copier uniquement le fichier JAR
COPY target/*.jar /code/

# Définir le répertoire de travail
WORKDIR /code/

# Exposer le port de l'application
EXPOSE 8094

# Commande pour exécuter l'application
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /code/*.jar
