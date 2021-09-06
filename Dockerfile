FROM adoptopenjdk:11-jre-hotspot

COPY target/url-shortener-*.jar /opt/url-shortener.jar

ENTRYPOINT ["java","-jar", "/opt/url-shortener.jar"]
