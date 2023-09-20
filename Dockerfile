FROM ubuntu/jre:8_edge

USER root

EXPOSE 8080
ADD target/challenge_3.jar challenge_3.jar
ENTRYPOINT ["java", "-jar", "challenge_3.jar"]