FROM arm64v8/eclipse-temurin:17-jdk
ARG JAR_FILE=build/libs/asic-service-0.1.jar
COPY ${JAR_FILE} asicservice.jar
EXPOSE 8071
ENTRYPOINT ["java","-jar","asicservice.jar"]