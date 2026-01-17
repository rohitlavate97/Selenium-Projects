# Automation Framework - Selenium + TestNG + Log4j + ExtentReports

## **1. Project Overview**

This is a **production-grade Selenium Automation Framework** designed for **web application testing**.  
It is built using:

- **Selenium WebDriver** for browser automation  
- **TestNG** for test execution, parallelism, and reporting  
- **Log4j 2** for logging and debugging  
- **ExtentReports** for interactive HTML reports  
- **Apache POI** for Excel-driven data  
- **TestNG Listeners** for screenshots on failures  
- **Cross-Browser Testing** (Chrome / Firefox)  
- **Environment Handling** (QA / UAT / PROD)  
- **Retry Mechanism** for flaky tests  

---

## **2. Features**

- Page Object Model (POM) design for maintainable page classes  
- Centralized configuration using **config-<env>.properties**  
- Thread-safe WebDriver for parallel execution  
- Retry mechanism for failed tests  
- Screenshot capture on failures  
- Assertion utilities for validation  
- Log4j logging for all test activities  
- Cross-browser and environment parameterization  

---

## **3. Project Structure**

AutomationFramework/
│
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/alchemist/
│  │  │     ├─ base/
│  │  │     │  ├─ BaseTest.java
│  │  │     │  └─ DriverFactory.java
│  │  │     │
│  │  │     ├─ constants/
│  │  │     │  └─ FrameworkConstants.java
│  │  │     │
│  │  │     ├─ exceptions/
│  │  │     │  └─ FrameworkException.java
│  │  │     │
│  │  │     ├─ listeners/
│  │  │     │  └─ TestListener.java
│  │  │     │
│  │  │     ├─ pages/
│  │  │     │  └─ LoginPage.java
│  │  │     │
│  │  │     ├─ retry/
│  │  │     │  └─ RetryAnalyzer.java
│  │  │     │
│  │  │     └─ utils/
│  │  │        ├─ AssertUtil.java
│  │  │        ├─ ConfigReader.java
│  │  │        ├─ ExcelUtil.java
│  │  │        ├─ ExtentManager.java
│  │  │        ├─ ScreenshotUtil.java
│  │  │        ├─ SeleniumUtil.java
│  │  │        └─ WaitUtil.java
│  │  │
│  │  └─ resources/
│  │     ├─ config.properties          # default
│  │     ├─ config-qa.properties       # QA environment
│  │     └─ log4j2.xml                 # logging configuration
│  │
│  └─ test/
│     └─ java/
│        └─ com/alchemist/tests/
│           └─ LoginTest.java
│
├─ reports/                            # ExtentReports HTML will be generated here
├─ screenshots/                         # Screenshots of failed tests
├─ logs/                                # automation.log
├─ drivers/                             # Local WebDriver executables
└─ pom.xml                              # Maven dependencies


---

## **4. Configuration**

### **4.1 Environment Properties**
Each environment has a separate properties file:

#config-qa.properties

URL=https://qa.example.com

BROWSER=chrome

#config-uat.properties

URL=https://uat.example.com

BROWSER=firefox

#config-prod.properties

URL=https://prod.example.com

BROWSER=chrome


### **4.2 Excel Test Data**
Store all test data in `src/main/resources/data.xlsx`:

| Sheet: ValidLogin |
|------------------|
| Username | Password | ExpectedTitle |
| user1    | pass1    | Dashboard    |

---

## **5. How to Run**

### **5.1 Using TestNG XML**
```bash
# Run tests for QA environment
mvn clean test -DsuiteXmlFile=testng.xml -Denv=qa

5.2 Parallel Execution

Set thread-count in testng.xml

WebDriver is thread-safe using ThreadLocal

5.3 Retry Failed Tests

Implemented via RetryAnalyzer

Max retries controlled in FrameworkConstants.RETRY_COUNT

6. Logging & Reporting

Log4j2 logs are stored in /logs/automation.log

ExtentReports generates interactive HTML reports in /reports/ExtentReport.html

Screenshots on failure saved in /screenshots/

7. Key Utilities

DriverFactory: Thread-safe browser initialization

SeleniumUtil: Reusable Selenium actions (click, type, jsClick, moveTo)

WaitUtil: Explicit waits

ConfigReader: Environment config loader

ExcelUtil: Read test data from Excel

ScreenshotUtil: Capture screenshots on failure

ExtentManager: Configure ExtentReports

RetryAnalyzer: Retry failed tests

8. Cross-Browser & Environment Handling

Browser is selected dynamically using BROWSER property

Environment (qa, uat, prod) is passed via testng.xml or Maven parameter:
<parameter name="env" value="qa"/>

9. Exception Handling

Custom exceptions are implemented via FrameworkException

All critical failures (like missing config, browser, Excel errors) throw this exception for clarity

10. Execution Flow
+-------------------+
| TestNG Suite Start|
+-------------------+
          |
          v
+---------------------------+
| Load environment (qa/uat/prod) |
+---------------------------+
          |
          v
+---------------------------+
| Initialize browser (Chrome/Firefox) |
+---------------------------+
          |
          v
+---------------------------+
| Execute Test using POM + SeleniumUtil |
+---------------------------+
          |
          v
+---------------------------+
| Retry failed tests (RetryAnalyzer) |
+---------------------------+
          |
          v
+---------------------------+
| Capture screenshots (on failure) |
+---------------------------+
          |
          v
+---------------------------+
| Generate ExtentReports + Log4j logs |
+---------------------------+
          |
          v
+-------------------+
| TestNG Suite End |
+-------------------+

11. Logging

All actions logged with Log4j:

Browser launch

URL navigation

Element interactions

Test pass/fail

Logs stored in logs/automation.log

12. Reporting

ExtentReports provides interactive HTML report:

Test status (pass/fail/skip)

Screenshots

Execution time

Report location: /reports/ExtentReport.html

13. Best Practices

Use POM design for maintainable pages

Store test data in Excel / properties

Implement retry mechanism for flaky tests

Use WaitUtil to avoid thread.sleep

Keep environment-specific data outside code

Always capture screenshots for failures


This version contains **everything**:  
- Project overview  
- Features  
- Project structure  
- Environment config  
- Excel test data  
- Execution steps  
- Logging & reporting  
- Utilities  
- Cross-browser & environment handling  
- Retry, exception handling  
- ASCII framework flow diagram  

---

If you want, I can also **create a single ready-to-use zip folder structure** with **all classes, utils, configs, and testng.xml**, so you just extract and run.  

Do you want me to do that next?
