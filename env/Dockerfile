FROM maven:3.9.1-openjdk-17 as build
ENV DOCKER_HOST=tcp://192.168.5.42:2375
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY ../pom.xml .
COPY .m2/settings.xml /usr/share/maven/conf/settings.xml
RUN mvn dependency:go-offline -B
COPY ../src src
RUN mvn package
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-oracle
ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-cp","app:app/lib/*","noe.roles.NoeRolesApplication"]ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-cp","app:app/lib/*","noe.roles.NoeRolesApplication"]