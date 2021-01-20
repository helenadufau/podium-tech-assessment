# podium-tech-assessment

The purpose of this repo is to implement a few tests for podium challenge, focused on podium.com.

Throughout this document the notation for user input variables is: `${VARIABLE}`.

These inputs are context-dependant and usually indicate that some kind of contextual parameter has to be passed over to the script or command in question.

---

## _Summary_

This document is organized into three sections, these can be seen below:
 
 Section 1: Test Plan, Technologies and Decisions
 
 Section 2: Optional Configuration
 
 Section 3: Running Instructions


---

##  1. Test Plan, Technologies and Decisions

####1.1 Test Plan:

Once the scope of the task was open, it was decided to test the availability of some elements and look for broken links on the page. One of the elements was present in the header, as the homepage is an introductory page that contains the header, and it displays links to pages that offer more detailed information according to the user's interest. So the display of the header elements was seen as a priority when it comes to testing, as users will likely be browsing this for information. Within the header, taking into account that every company that produces something for the market has the intention of
 exhibiting to sell their products, the appropriate display of the products was tested. For the same reason, it was tested whether the correct link to the pricing page was displayed. Considering then that this same company intends to retain its customers, an important point is that they are able to easily find the login page and access the products / resources purchased, so it was tested if the correct link to the login page was being used. It is worth mentioning that for these tests it was assumed that the current state of the links was correct. In addition, a test was carried out looking for broken links on the homepage, as this is a large "link map" that contains relevant information about the company and it can be difficult to keep track of when one of these is broken.
 
####1.2 Technologies:

Java 11

Maven 3.6

Selenium Java.3.141.59

Testng 7.3.0

Maven Surefire Plugin 3.0.0-M5

Webdrivermanager 4.2.2

Dotenv Java 2.2.0 

Log4j Core 2.13.2 

Log4j api 2.13.1

Log4j slf4j impl 2.13.1

Jansi 1.18 

####1.3 Decisions:


- Selenium (Java)

I chose to use Selenium because it is a robust tool for browser automation, due to the range of materials available online about and the quality community. Regarding Java, the decision was based on familiarity with the language.

 - TestNG

JUnit is commonly used for tests with Selenium, but as its name already suggests it was developed for unit testing. With that in mind, TestNG was the choice made, as it is designed to cover all categories of tests: unit, functional, end to end, integration, etc. In addition, it is very flexible and provides a wider range of features.

- Test Listener and Log4j

For better visualization of the tests a listener was implemented to print relevant information in relation to the tests in execution. To display these logs, Log4j was used, which is the tool most used by the Java community for the purpose of creating logs.


- Dotenv Java

In order to centralize configurations and make the tester's life easier, an .env was used to update variables that influence behavior and can be commonly changed. 

##  2. Optional Configuration

The project settings are in a .env, however these have already been previously configured with the default values. If you want to change any, their use was mentioned below.

-  Domain:

 `DOMAIN=${yourDomain}`

The default domain is podium.com, but you can change for any desired domain, but your tests won't work once they are designed for podium.com.

-  Protocol:

 `PROTOCOL=${protocol}`

The default protocol is htpps.

- Browser

`BROWSER=${testsBrowser}`

Until now, tests are developed focused on Google Chrome, so it's highly recommended to set browser as `chrome`. The organization of the project was made in order to make possible the creation of other `${BrowserName}Manager.java` and the insertion of these in the DriverFactory switch, however it was not yet implemented.
- Headless

`HEADLESS=${boolean}`

Headless browser is a way to run the browser in a headless environment without the full browser UI. To develop tests and debug is necessary use headless as `false`, but if you just want to run the tests on your container the recommended/default configuration is `true`.

- Browser Close

`BROWSER_CLOSE=${boolean}`

After a test run it's highly recommended to close all browser instances to avoid memory overhead, thereby your default configuration should be `true`. When we are trying to debug tests or testing feature, it's useful to not close the browser after the test, so you can analyze the screen behavior, it's possible to do that setting this variable to `false`.

##  2. Running Instructions

You can build your image using this repository docker file with:

`docker build -t ${imageName} .`

Or you can download the image:

`docker pull helenadufau/podium` 

To have access to your container:

`docker container run -it ${imageName} /bin/bash`

Once inside your container clone this repository with :
`git clone https://github.com/helenadufau/podium-tech-assessment.git` 

After that you have a few running options:

- Running all existent tests

 `mvn clean test`

- Running a set of methods in a single test class:

 1. You should use the following syntax

 `mvn clean -Dtest=${className}#${methodName} test`

 2. You can select multiple methods

 `mvn clean -Dtest=${className}#${testeName}+${anotherTestName} test`

For more running information and options access the [documentation of Maven Surefire Plugin](http://maven.apache.org/surefire/maven-surefire-plugin/index.html).

###* Info
The searching for broken links test could take a while, like several minutes. I would recommend you to run and validate the other tests first with:

`mvn clean -Dtest=PodiumBasicTests#showingAllHeaderElements+showingAllProducts+loginLink+pricingLink test`

And after let running only the broken links tests and go take a coffee until is ready, using:

`mvn clean -Dtest=PodiumBasicTests#brokenLinksOnHomePage test`

If you decided to run all together they will work too, but I beleive this way is painless. It's possible to do suites and groups to make running instructions smaller, but since it's just one class case it was'nt implemented. 

 