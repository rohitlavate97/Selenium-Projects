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
