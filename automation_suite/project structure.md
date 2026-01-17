selenium-testng-framework
│
├── pom.xml
├── testng.xml
├── Jenkinsfile
│
├── logs
│   └── automation.log
│
├── screenshots
│   └── testName_timestamp.png
│
├── reports
│   └── ExtentReport.html
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── alchemist
│   │   │           │
│   │   │           ├── base
│   │   │           │   ├── BaseTest.java
│   │   │           │   └── DriverFactory.java
│   │   │           │
│   │   │           ├── constants
│   │   │           │   └── FrameworkConstants.java
│   │   │           │
│   │   │           ├── listeners
│   │   │           │   └── TestListener.java
│   │   │           │
│   │   │           ├── retry
│   │   │           │   └── RetryAnalyzer.java
│   │   │           │
│   │   │           ├── utils
│   │   │           │   ├── ConfigReader.java
│   │   │           │   ├── ExcelUtil.java
│   │   │           │   ├── ExtentManager.java
│   │   │           │   ├── ScreenshotUtil.java
│   │   │           │   ├── SeleniumUtil.java
│   │   │           │   └── WaitUtil.java
│   │   │           │
│   │   │           └── pages
│   │   │               └── LoginPage.java
│   │   │
│   │   └── resources
│   │       ├── config.properties
│   │       ├── log4j2.xml
│   │       └── data.xlsx
│   │
│   └── test
│       └── java
│           └── com
│               └── alchemist
│                   └── tests
│                       └── LoginTest.java
│
└── README.md
