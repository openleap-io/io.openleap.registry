FROM adoptopenjdk/openjdk11-openj9:jre-11.0.7_10_openj9-0.20.0-alpine
VOLUME /tmp
ARG JAVA_OPTS="-Xshareclasses -Xquickstart -noverify"
ADD target/openwms-services.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar
