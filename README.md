## Development requirements:

**for developing you need:**
+ Java 11
+ gradle
+ node.js (if not installed with node you also need **npm**)
+ vue cli (can be installed via **npm install -g @vue/cli**)



## Development instructions:

# Setup
When first cloning the contents of this branch you need to navigate 
into the **src/frontend** directory via terminal and run: 

> npm install

This will download all necessary node_modules for the frontend component.


# Usage
After installing all dependencies for vue, we can run our frontend (still in **src/frontend**)
from terminal via:

> npm run serve

this command should start a vue application which is hosted on **localhost:3000**.

To run our backend simultaneously you need to navigate via terminal into the root directory 
of this project and enter:

> gradle bootRun

which will launch our backend application on **localhost:8080**.
Now both applications run and are accessible via a browser.


# Building production ready jar's **(CURRENTLY NOT AVAILABLE)**
If you want to build a production ready jar file which contains our compiled frontend application,
then you have to run:

> gradle build

via terminal in your projects root directory.

To run and test the jar file you need to navigate into **build/libs** there you should find a file 
called something like **wstgsh-0.0.1-SNAPSHOT.jar**.
Run:

> java -jar **name-of-jarfile**

After this command a web application using our java backend will be hosted on **localhost:8080**.
This application returns our vue frontend as their static content so if you enter:
**localhost:8080** in your browser, you will see our vue application.