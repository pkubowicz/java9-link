Demonstrates benefits from using `jlink` utility from Java 9 to create a custom runtime image.

## Quick Start

Running with Docker:

```
gradle image
docker run --rm --name=greeter -p 8000:8000 java9-link:jre9
```

Running custom JRE directly:

```
gradle build
./build/custom-jre/bin/java -m example.greeter.server/example.greeter.server.Runner
```

Running with Java 9 using modules:

```
/usr/lib/jvm/jdk-9/bin/java --module-path /usr/lib/jvm/jdk-9/jmods:greeter-protocol/build/libs:greeter-server/build/libs -m example.greeter.server/example.greeter.server.Runner
```
or using classpath:
```
/usr/lib/jvm/jdk-9/bin/java -cp greeter-protocol/build/libs/greeter-protocol.jar:greeter-server/build/libs/greeter-server.jar example.greeter.server.Runner
```

Checking against Java 8:

```
gradle image -PjavaVersion=1.8
docker run --rm --name=greeter -p 8000:8000 java9-link:jre8
```

or

```
gradle build -PjavaVersion=1.8
java -jar greeter-server/build/libs/greeter-server-all.jar
```
