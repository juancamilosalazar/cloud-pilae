FROM anapsix/alpine-java:8
WORKDIR /app
RUN apk add --no-cache tzdata
ENV TZ America/Bogota
ADD api/build/libs/*.jar /app/
RUN mv *.jar app.jar
EXPOSE 8443
CMD java -jar /app/app.jar