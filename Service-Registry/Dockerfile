from openjdk:17



ARG JAR_FILE=target/*.jar

copy ${JAR_FILE} serviceregistry.jar


ENTRYPOINT ["java","-jar","/serviceregistry.jar"]

expose 8761
