## Development requirements:

**for developing you need:**
+ Java 11
+ gradle
+ node.js (if not installed with node you also need **npm**)


## Development instructions:

# Setup

**Gradle**
If you are developing on a machine with no gradle installed you can use the Gradle Wrapper.
For first time initialisation enter:
> ./gradlew wrapper --gradle-version=6.8.3 --distribution-type=bin

this will configure the gradle wrapper to download and use the set gradle binaries.
After this you can enter any **gradle** command by using **'./gradlew''**, 
or **'gradlew'** instead of **'gradle'**.
Example:
> gradle bootRun

can be executed as:
> gradlew bootRun

**Vue**
When first cloning the contents of this branch you need to navigate 
into the **src/frontend** directory via terminal and run: 

> npm install

This will download all necessary node_modules for the frontend component.


# Usage
After installing all dependencies for vue, we can run our frontend (still in **src/frontend**)
from terminal via:

> npm run serve

this command should start a vue devserver which is hosted on **localhost:3000**.
The devserver provides handy functionalities like hot reaload for example.

To run our backend simultaneously you need to navigate via terminal into the root directory 
of this project and enter:

> gradle bootRun

which will launch our backend application on **localhost:8080**.
Now both applications run and are accessible via a browser.


# Building production ready jar's
If you want to build a production ready jar file which contains our compiled frontend application,
then you have to run:

> gradle build -P full

via terminal in your projects root directory.
With the **'-P full'** flag you tell gradle to run the npmBuild and copyFrontendToBuild tasks, 
which wil build and integrate our vue frontend into our jar file.
**Note** that when you build your java application with your inte

To run and test the jar file you need to navigate into **build/libs** there you should find a file 
called something like **wstgsh-0.0.1-SNAPSHOT.jar**.
Run:

> java -jar **name-of-jarfile**

After this command a web application using our java backend will be hosted on **localhost:8080**.
This application returns our vue frontend as its static content so if you enter:
**localhost:8080** in your browser, you will see the vue application.