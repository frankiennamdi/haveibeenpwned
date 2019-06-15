# Description

This project is a client to the popular https://haveibeenpwned.com/API/v2 site and supports
some of the services

# Technologies
1. gradle (included)
2. Java 8.x - chosen for some of its appealing functionality like streams, functions and method references.
3. Developed with IDEA Intellij - chosen as my IDE of choice.
4. Spring Boot

# Tests

All the test are integration test as they access the **haveibeenpwned** site. So
it is possible to reach the rate limit if run repeatedly. This gives us
100 percent coverage. In the future some unit tests can be added.

# Build

```
./gradlew clean build"
```

# Execution
Below are some examples of how to execute the application.


### Printing the services supported

```
./pwned
```

or

```
./gradlew -q bootRun -Pargs=""
```

output:

```
usage: haveibeenpwned.jar
 breach
 breachedaccount
 breaches
 pasteaccount
````

### breachedaccount

```
./pwned breachedaccount -account=test@example.com -domain=adobe.com -truncateResponse=true -includeUnverified=true
```


```
./gradlew -q bootRun -Pargs="breachedaccount -account=test@example.com -domain=adobe.com -truncateResponse=true -includeUnverified=true"
```


### breaches

```
./pwned breaches -domain=adobe.com
```


```
./gradlew -q bootRun -Pargs="breaches -domain=adobe.com"
```

### pasteaccount

```
./pwned pasteaccount -account=test@example.com
```


```
./gradlew -q bootRun -Pargs="pasteaccount -account=test@example.com"
```

### breach

```
./pwned breach -name=adobe
```


```
./gradlew -q bootRun -Pargs="breach -name=adobe"
```


# Usage

```
./pwned [command] -help
```


```
./gradlew -q bootRun -Pargs="[command] -help"
```


## e.g

```
./pwned breach -help
```


```
./gradlew -q bootRun -Pargs="pasteaccount -help"

```



