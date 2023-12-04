from openjdk:17



ARG JAR_FILE=target/*.jar

copy ${JAR_FILE} configserver.jar


ENTRYPOINT ["java","-jar","/configserver.jar"]

expose 9296
