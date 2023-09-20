FROM openjdk

WORKDIR /app

COPY target/finance-0.0.1.jar /app/finance.jar

ENTRYPOINT["java", "-jar", "finance.jar"]