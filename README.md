# Description

# Components

# Build

```
./gradlew clean build"
```

# Execution
Below are some examples of how to execute the application.

### breachedaccount

```
./gradlew -q bootRun -Pargs="breachedaccount -account=test@example.com -domain=adobe.com -truncateResponse=true -includeUnverified=true"
```


```
java -jar build/libs/haveibeenpwned-1.0-SNAPSHOT.jar breachedaccount -account=test@example.com -domain=adobe.com -truncateResponse=true -includeUnverified=true
```

### breaches

```
./gradlew -q bootRun -Pargs="breaches -domain=adobe.com"
```

```
java -jar build/libs/haveibeenpwned-1.0-SNAPSHOT.jar breaches -domain=adobe.com
```


### pasteaccount
```
./gradlew -q bootRun -Pargs="pasteaccount -account=test@example.com"
```

```
java -jar build/libs/haveibeenpwned-1.0-SNAPSHOT.jar pasteaccount -account=test@example.com
```

### breach

```
./gradlew -q bootRun -Pargs="breach -name=adobe"
```

```
java -jar build/libs/haveibeenpwned-1.0-SNAPSHOT.jar breach -name=adobe
```

# Usage

```
./gradlew -q bootRun -Pargs="[command] -help"
```

```
java -jar build/libs/haveibeenpwned-1.0-SNAPSHOT.jar [command] -help
```

## e.g

```
./gradlew -q bootRun -Pargs="pasteaccount -help"

```

```
java -jar build/libs/haveibeenpwned-1.0-SNAPSHOT.jar breach -help
```

