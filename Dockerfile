FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","-Dpatient.proxy.url=http://172.17.0.3:8081/","-Dnote.proxy.url=http://172.17.0.5:8083/","/app.jar"]