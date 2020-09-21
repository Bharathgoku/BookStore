FROM openjdk:8

COPY ./target/BookStore-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

RUN sh -c 'touch BookStore-1.0-SNAPSHOT.jar'

CMD ["java", "-jar", "BookStore-1.0-SNAPSHOT.jar", "--server.port=8080"]