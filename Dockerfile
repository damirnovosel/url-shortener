FROM adoptopenjdk:11-jre-hotspot

COPY target/*runner.jar /opt/url-shortener.jar
COPY target/lib/ /opt/lib

ENTRYPOINT ["java","-jar", "/opt/url-shortener.jar"]
