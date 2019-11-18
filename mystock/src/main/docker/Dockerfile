FROM openjdk:8-jdk

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME /tmp

ADD mystock-0.0.1-SNAPSHOT.jar app.jar

RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS="-Xms256m -Xmx256m"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 8080