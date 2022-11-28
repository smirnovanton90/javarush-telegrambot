FROM eclipse-temurin:11
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_javarush_anton_bot
ENV BOT_TOKEN=5923926757:AAE_A-ySsSyBI4G2HCfKTgivPqRU-hx2kDk
ENV BOT_DB_USERNAME=jrtb_db_user
ENV BOT_DB_PASSWORD=jrtb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.datasource.password=${BOT_DB_PASSWORD}", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.username=${BOT_DB_USERNAME}", "-jar", "app.jar"]
