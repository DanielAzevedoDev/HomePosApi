# Usar uma imagem base do JDK 17
FROM eclipse-temurin:17-jdk-jammy

LABEL title="home-api"
LABEL version="0.0.1-SNAPSHOT"

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o build do Spring Boot para dentro do container
COPY target/home-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta da aplicação (modifique conforme necessário)
EXPOSE 8080

# Comando para rodar o aplicativo no container
CMD ["java", "-jar", "app.jar"]