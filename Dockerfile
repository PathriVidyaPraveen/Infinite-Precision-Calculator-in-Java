FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN javac arbitraryarithmetic/AInteger.java \
         arbitraryarithmetic/AFloat.java \
         MyInfArith.java

ENTRYPOINT ["java", "MyInfArith"]
