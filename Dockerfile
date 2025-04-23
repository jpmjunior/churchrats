# Etapa 1: Construir o projeto usando a imagem do Maven
FROM maven:3.9.9-ibm-semeru-21-jammy AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY ./pom.xml ./
COPY ./src ./src

# Executa o comando para compilar o projeto
RUN mvn clean package -DskipTests


# Etapa 2: Usar uma imagem menor do JRE para rodar a aplicação
FROM openjdk:21 AS deploy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR gerado na fase anterior
COPY --from=build /app/target/churchrats-0.0.1-SNAPSHOT.jar /app/churchrats.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/churchrats.jar"]

