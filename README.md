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
./build/custom-jre/bin/java -m example.server/example.server.Runner
```

Checking against Java 8:

```
gradle image -PjavaVersion=1.8
docker run --rm --name=greeter -p 8000:8000 java9-link:jre8
```

or

```
gradle build -PjavaVersion=1.8
java -jar build/libs/java9-link.jar
```
