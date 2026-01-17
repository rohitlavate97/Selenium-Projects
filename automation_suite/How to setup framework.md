==============================
Selenium + TestNG Automation Framework Notes
==============================

1️⃣ Project Planning
- Purpose: Web UI automation for regression or end-to-end testing.
- Tech stack:
    - Language: Java 8+
    - Automation: Selenium WebDriver
    - Test framework: TestNG
    - Build tool: Maven
    - Reporting: ExtentReports
    - Logging: Log4j2
    - Data source: Excel/CSV/DB (optional)
    - Parallel execution: TestNG + Selenium Grid
- Design pattern: Page Object Model (POM)
- Utilities: reusable actions (clicks, waits, screenshots)
- Environment management: Properties files for QA, UAT, PROD

2️⃣ Folder/Package Structure
src/
├─ main/java/com/yourcompany/
│  ├─ base/           -> BaseTest, DriverFactory
│  ├─ constants/      -> Framework constants
│  ├─ exceptions/     -> Custom exceptions
│  ├─ listeners/      -> TestNG listeners (logs, reports)
│  ├─ pages/          -> Page Object classes
│  ├─ retry/          -> RetryAnalyzer
│  └─ utils/          -> Helper utilities (Excel, Wait, Selenium actions)
└─ test/java/com/yourcompany/tests/ -> Test classes
resources/
├─ config.properties, config-qa.properties, etc.
├─ log4j2.xml
├─ data.xlsx (optional)
drivers/
reports/
screenshots/
logs/
pom.xml

3️⃣ Core Framework Components

a) BaseTest.java
- Initialize WebDriver before each test
- Load environment config
- Quit WebDriver after each test
- ThreadLocal<WebDriver> for parallel safety
- @BeforeMethod -> setup browser, navigate to URL
- @AfterMethod -> quit browser

b) DriverFactory.java
- Handles local and remote (Grid) WebDriver initialization
- Supports Chrome, Firefox, Edge
- Maximize window, set timeouts
- If GRID=true, create RemoteWebDriver
- Thread-safe using ThreadLocal<WebDriver>

c) ConfigReader.java
- Load environment-specific properties (URL, GRID, GRID_URL)
- Throws exception if config not loaded

d) FrameworkConstants.java
- Excel file path
- Screenshot path
- Wait times
- Retry count

e) RetryAnalyzer.java
- Retry failed tests automatically
- Controlled via RETRY_COUNT

f) Utilities
1. SeleniumUtil.java – click, type, hover, double-click, drag & drop, JS executor, alerts, frames, windows, cookies, shadow DOM
2. WaitUtil.java – explicit waits, fluent waits
3. ExcelUtil.java – fetch test data
4. ScreenshotUtil.java – screenshots on failure
5. AssertUtil.java – soft assertions
6. ExtentManager.java – configure ExtentReports

g) Page Objects (POM)
- Each page gets a separate class
- Only store WebElements and actions
- Example: LoginPage.java -> login(username, password)

h) Test Classes
- Keep business logic in test methods
- Fetch data from Excel/DataProvider
- Use soft assertions and retry analyzer
- @Test(retryAnalyzer = RetryAnalyzer.class)

i) Listeners
- Implement ITestListener
- Capture logs, screenshots, update ExtentReports

j) Logging
- Use Log4j2
- Separate logs: info, debug, error

k) Reporting
- Use ExtentReports
- Single instance via ExtentManager
- Add screenshots on failure

l) Data-Driven Testing
- Excel files + Apache POI
- Use in tests or via TestNG DataProvider

m) Selenium Grid Integration
- config.properties:
    GRID=true
    GRID_URL=http://localhost:4444
- DriverFactory decides local vs Grid
- Parallel execution via TestNG XML:
    <suite parallel="tests" thread-count="3">

n) Maven POM
- Dependencies: Selenium, TestNG, Apache POI, ExtentReports, Log4j2, WebDriverManager, Commons IO
- Plugins: Surefire or Failsafe

o) Best Practices
- Tests should be independent
- ThreadLocal<WebDriver> for parallel execution
- No hardcoded data, use config & Excel
- PageObjects clean (only actions & locators)
- Retry for flaky tests
- Screenshots on failure
- Structured logging
- ExtentReports for client-friendly reports

p) Steps to Write Framework from Scratch
1. Create project structure
2. Add Maven dependencies
3. Write BaseTest + DriverFactory
4. Implement ConfigReader + FrameworkConstants
5. Create Selenium utilities (click, type, waits, JS, alerts, frames)
6. Implement WaitUtil
7. Implement ScreenshotUtil & ExtentReports
8. Create Page Objects (POM)
9. Write Tests using Excel or DataProvider
10. Implement RetryAnalyzer and TestNG Listeners
11. Integrate Log4j2
12. Add Selenium Grid support in DriverFactory
13. Configure parallel execution in TestNG XML
14. Test locally, then run on Grid
15. Generate reports and logs

✅ Following these notes will give you a production-ready, parallel, data-driven Selenium + TestNG framework with Grid support.
