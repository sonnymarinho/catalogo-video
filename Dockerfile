FROM openjdk:11

USER gradle
VOLUME "/home/gradle/.gradle"
WORKDIR /home/gradle