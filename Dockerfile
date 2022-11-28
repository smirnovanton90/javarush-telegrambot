FROM eclipse-temurin:11
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_javarush_anton_bot
ENV BOT_TOKEN=5923926757:AAE_A-ySsSyBI4G2HCfKTgivPqRU-hx2kDk
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]
