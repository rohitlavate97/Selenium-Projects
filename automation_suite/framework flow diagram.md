TestNG.xml
   ↓
Test Class (LoginTest)
   ↓
BaseTest (@BeforeMethod)
   ↓
DriverFactory (ThreadLocal WebDriver)
   ↓
Page Object (LoginPage)
   ↓
WaitUtil + Selenium Actions
   ↓
Assertion (TestNG)
   ↓
Listener
   ├── Screenshot on failure
   ├── Log4j logging
   └── Extent Report update
