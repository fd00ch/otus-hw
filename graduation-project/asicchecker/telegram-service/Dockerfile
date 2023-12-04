FROM arm64v8/eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/telegram-service-0.1.jar
COPY ${JAR_FILE} telegramservice.jar
EXPOSE 8072
ENTRYPOINT ["java","-jar","telegramservice.jar"]