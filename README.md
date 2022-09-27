# PayufinLogger
This is Custom Payufin logger which you can integrate with your repos and log via log4j2. This library contains custom levels which you can use to log your things.

### How to Integrate:

1. Add Logging jar inside the libs folder inside resources

2. Remove Logback or Log4j2.xml files if you have any in your code.

3. Copy lombok.config file from [here](lombok.config), and paste it in the same directory where your Main Application class is present.

4. Include below dependencied in you pom file

```xml
<dependency>
	<groupId>com.payu.payufinLogging</groupId>
	<artifactId>PayufinLogging</artifactId>
	<version>1.0.0-SNAPSHOT</version>
	<scope>system</scope>
	<systemPath>${basedir}/src/main/resources/libs/PayufinLogging-1.0.0-SNAPSHOT.jar</systemPath>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>

<dependency>
	<groupId>org.apache.logging.log4j</groupId>
	<artifactId>log4j-layout-template-json</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
```

5. Remove `spring-boot-starter-logging` wherever it is present using below code

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```


6. At last you can remove @Log4j2, @Log4j or @Slf4j from the top of class and replace it with @CustomLog annotation from lombok to you Payufin Logger.






