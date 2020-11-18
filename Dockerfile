FROM openjdk:12-alpine
ADD HelloWorld.java .
RUN javac HelloWorld.java
CMD ["java", "HelloWorld"]