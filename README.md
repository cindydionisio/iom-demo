# iom-demo
This is a simple upload and read file for IOM which implements a simple Basic Auth


# Pre-requisites
```
Spring Boot 2.0.0 or later
Java 8 or later
Maven 3.0.0 or later
```

### Installing

Copy/clone this entire folder to your Spring/bin dir
For first time running:
```
mvn install
```
For creating executable JAR:
```
mvn package
```
To run locally, go to iomdemo dir and run (still depends on the snapshot version)

```
mvn clean install && java -jar target/iomdemo-0.0.1-SNAPSHOT.jar
```

## Usage
This app uses Basic Auth where username and password is required for every transction. 
Update application.properties
```
app.rootdir - this will be the read and write directory for file upload.
app.usercredentials - dir where users.csv will be saved (username and encrypted password pairs)
app.adminuser - super user (in case no new users.csv is existing/ empty)
app.adminpassword - password of super user
```
To add a user, navigate to {baseurl}/iomdemo/user-mngmt/user/{username}:
```
This transaction still uses Basic Auth (you can use super user account).
Password needs to be in the Request Body (POST)
```

For "/fileupload/" url transactions where Filename is required, the extension should be set on the "Content-Type"
Current supported files:
```
text/csv (.csv)
text/plain (.txt) - default
text/xml (.xml)
application/json (.json)
text/html (.html)
```

## Deployment

Run this command to create a jar file with all its dependencies
```
mvn package
```
