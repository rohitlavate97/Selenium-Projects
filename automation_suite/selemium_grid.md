# Selenium Grid Setup for AutomationFramework

## 1️⃣ Download Selenium Grid

1. Go to [Selenium Downloads](https://www.selenium.dev/downloads/)
2. Download **Selenium Server (Grid)** `.jar` file (e.g., `selenium-server-4.17.0.jar`)
3. Place it somewhere like `C:\selenium\`

---

## 2️⃣ Start the Grid Hub

# Default Hub on port 4444
java -jar selenium-server-4.17.0.jar hub

# Optional: specify a custom port
java -jar selenium-server-4.17.0.jar hub --port 5555

Hub UI: http://localhost:4444/ui

3️⃣ Start Nodes
Auto-detect drivers on same machine:
java -jar selenium-server-4.17.0.jar node --detect-drivers

Specify Hub URL and custom port:
java -jar selenium-server-4.17.0.jar node --hub http://localhost:4444 --port 5556

Optional: Node JSON configuration (nodeConfig.json):
{
  "capabilities": [
    { "browserName": "chrome", "maxInstances": 3 },
    { "browserName": "firefox", "maxInstances": 2 }
  ],
  "configuration": {
    "port": 5556,
    "hub": "http://localhost:4444",
    "nodeTimeout": 120,
    "maxSession": 5
  }
}


Start Node with:

java -jar selenium-server-4.17.0.jar node --config nodeConfig.json

4️⃣ Update Framework Config

config.properties or config-qa.properties:

GRID=true
GRID_URL=http://localhost:4444

5️⃣ Run Tests on Grid
# Example using Maven
mvn clean test -Dbrowser=chrome -Denv=qa


Your framework will automatically connect to the Grid Hub.

6️⃣ Parallel Execution (TestNG XML)
<suite name="ParallelSuite" parallel="tests" thread-count="3">
    <test name="ChromeTest">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="com.alchemist.tests.LoginTest"/>
        </classes>
    </test>

    <test name="FirefoxTest">
        <parameter name="browser" value="firefox"/>
        <classes>
            <class name="com.alchemist.tests.LoginTest"/>
        </classes>
    </test>
</suite>


Runs Chrome and Firefox tests simultaneously on Grid nodes.

✅ Tips

Ensure Grid and Nodes have correct browser drivers installed.

Hub URL (GRID_URL) must match the actual Hub URL.

ThreadLocal<WebDriver> ensures thread safety for parallel execution.

You can scale nodes across multiple machines for large test suites.


---

If you want, I can also make a **more compact “one-command setup” snippet** for Windows/Linux that **starts Hub + 2 Nodes + Chrome/Firefox/Edge** automatically so your `.md` file becomes a ready-to-run guide.  

Do you want me to do that?