# PayufinLogger
This is Custom Payufin logger which you can integrate with your repos and log via log4j2. This library contains custom levels which you can use to log your things, and also this will provide a functionality to update the log level at runtime.

### How to Integrate:

1. First in local you need to add Jfrog settings in your .m2/settings.xml file in order to fetch jar form Jfrog
Check this for latest settings - [here](https://payufin.atlassian.net/wiki/spaces/NEOB/pages/862224863/Integration+Repository+and+JFrog+artifactory)

2. You need to add below jar dependency in you pom file, jar version is already deployed to Jfrog, You may need to hard code some versions of `spring-boot-starter-log4j2`, `log4j-core`, `log4j-api`, `log4j-jul`, `log4j-layout-template-json` only when if you have lower versions of spring below than 2.4.6.

```xml
<dependencies>
...
	
	<dependency>
		<groupId>com.payufin.integration.payufinLogger</groupId>
		<artifactId>payufin.integration.payufinLogger</artifactId>
		<version>1.0.0-SNAPSHOT</version>
	</dependency>
	
...
<dependencies>

```

3. You also need to add Jfrog artifactory link in your pom file in order to fetch jar from there.
```xml

<project>
...
	<repositories>
		<repository>
			<id>libs-release-local</id>
			<name>libs-release-local</name>
			<url>https://jfrog-artifactory.lazypay.in:443/artifactory/libs-release-local/</url>
		</repository>
		<repository>
			<id>libs-snapshot-local</id>
			<name>libs-snapshot-local</name>
			<url>https://jfrog-artifactory.lazypay.in:443/artifactory/libs-snapshot-local/</url>
		</repository>
	</repositories>
...

<project>

```

4. You need remove your Logback or Log4j2.xml files if you have any in your code so that it will automatically pick from the Jar, You will see below line in console when it get picked by Jar.

```
classpath:EcsLayout.json found 2 resources, using the first one: jar:file:/Users/lakshya.jain/.m2/repository/com/payufin/integration/payufinLogger/payufin.integration.payufinLogger/1.3-SNAPSHOT/payufin.integration.payufinLogger-1.0.0-SNAPSHOT.jar!/EcsLayout.json
```


5. Remove `spring-boot-starter-logging` wherever it is present using below code, <br> Note - You need exclude it from every dependency from wherever it is getting included

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

6. Copy lombok.config file from [lombok.config](src/main/java/com/payufin/integration/payufinLogger/lombok.config), and paste it in the same directory where your Main Application class is present.

7. You need to define below System properties in your jenkins file in order to use the framework, In local you can add it via VM options.
Visit [here](https://payufin.atlassian.net/wiki/spaces/EN/pages/1123123207/Logging+Framework) to get more information about this attributes.

```
-DlogPath=var/log/application
-DserviceName=<YOUR SERVICE NAME>
-DrootLogLevel=INFO
-DconsoleLogMinLevel=SECURITY
-DconsoleLogMaxLevel=INFO
-DfileLogMinLevel=SECURITY
-DfileLogMaxLevel=DEBUG
```
8. Now if you want to change log levels at runtime without deploying the application again, We have provided a hook for it, you need to implement 
[LoggingConfigProvider](src/main/java/com/payufin/integration/payufinLogger/services/LoggingConfigProvider.java), and in getLoggingConfigFromSource you can fetch the config value from DB and return via [LoggingConfigModel](src/main/java/com/payufin/integration/payufinLogger/models/LoggingConfigModel.java)

9. Now in order to update logging config when your application or new instance starts, you have to autowire [LoggingConfigService](src/main/java/com/payufin/integration/payufinLogger/services/LoggingConfigService.java) in your service application file and call its refreshLoggingConfigs method in PostConstruct function call.
You also need to add below line on top of your application class in order to create bean from the logging jar.

    `@ComponentScan(basePackages = {"com.payufin.integration"})`

```java

@PostConstruct
public void postConstruct(){
    loggingConfigService.refreshLoggingConfigs();
}

```

10. If you want to update the logging configs of current active pods then you have to update the configs value in DB and then run the script which we will provide, and it will update the logging configs of all current active pods.
Visit [here](https://payufin.atlassian.net/wiki/spaces/EN/pages/1123123207/Logging+Framework) to get more information regarding this.

11. At last you can remove @Log4j2, @Log4j or @Slf4j from the top of class and replace it with @CustomLog annotation from lombok to you Payufin Logger, 
you can use it custom levels DIAGNOSE and SECURITY to have better logging and Most importantly Cost Saving !!


