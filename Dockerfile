FROM openjdk:21-jdk
MAINTAINER "Minhaj"
COPY target/metsetmerveilles-*.war metsetmerveilles.war
ENTRYPOINT ["java","-jar","/metsetmerveilles.war"]
EXPOSE 8080