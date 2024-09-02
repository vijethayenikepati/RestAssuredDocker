m## Overview

This project is a REST API test framework built to automate the testing of RESTful APIs 
using JUnit 5, Rest-Assured, and Cucumber. The framework supports parallel test execution
to enhance efficiency and reduce execution time. It is developed using Maven and leverages
Java 17 features.

## Prerequisites
- Java 17
- Maven 3.x

## Setup

1. Clone the Repository:

   git clone https://github.com/vijethayenikepati/RestAssuredAssignment.git

2. For Run Tests:
   ```sh
   mvn clean install

3. For Parallel execution:
   ```sh
   mvn verify

After running the tests, the reports generated found at `target/cucumber-reports.html` directory.
Open the report using a web browser to review the results.

Working in IDE:

1.Navigate to "src/test/java/com/api/runners"
2.Go to TestRunner.java and run
3.Rerun Failed Tests using FailedRunner.java

Docker File:
1. Go to Project Foleder -
   cd ApiTestingFramework
2. Build an image with the following command
   docker build -t apitestimage:1 .
3. Run docker image
   docker run apitestimage:1

The below steps are Optional:
To build image from the above container, we can build an image and
push to repo

OPTIONAL: (FOR  LEARNING )
Create an image on top on all the dependencies  (Rename to Dockerfile1 to Dockerfile)

1. docker commit containerid (After running above steps, commit gives an image with none)
2. docker tag imageid newimagename  (imageid from step 1)
3. docker login (if required)
4. docker push account/imagename:tagname
   Ex: docker tag 793fc225af2c vijetha/mytestapiimage:latest


Running on EC2 instance:
1.Spin EC2 instance machine on AWS
2.Open cmd and navigate to pem file location on local.
3.Enter ssh command from EC2 and enter. (Remove double quotes and ec2-user in place of  root)
  Ex: ssh -i pemfilename ec2-user@xxx.amazonaws.com
4.After step 3, we are inside ec2 instance.
5.Enter docker and check if it is installed








   

