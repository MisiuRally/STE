FROM amazoncorretto:17-alpine3.17

ADD target/UltimaSport-1.0.jar .
EXPOSE 8191
CMD java -jar UltimaSport-1.0.jar
