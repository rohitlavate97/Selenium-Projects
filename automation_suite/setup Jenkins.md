==============================
CI Setup in Jenkins for Selenium + TestNG Framework
==============================

1️⃣ Prerequisites
-----------------
1. Jenkins installed (local or server)
2. Java installed on Jenkins server (JDK 8+)
3. Maven installed on Jenkins server
4. Git installed (for source code)
5. Optional: Selenium Grid installed for parallel/remote execution

2️⃣ Prepare Your Selenium Framework
-----------------------------------
1. Organize project:
   - src/main/java/com/yourcompany/... (Base, Utils, Pages)
   - src/test/java/com/yourcompany/tests/... (Test classes)
   - resources/ (config.properties, config-qa.properties, log4j2.xml)
   - pom.xml (dependencies for Selenium, TestNG, ExtentReports, Log4j2, Apache POI)
2. Ensure framework supports:
   - Environment config (config.properties, config-qa.properties)
   - Browser selection via properties or parameters
   - Thread-safe WebDriver (ThreadLocal)
   - Reports (ExtentReports) and Screenshots
   - RetryAnalyzer for flaky tests

3️⃣ Create Jenkins Job
----------------------
Option 1: Freestyle Project
1. Open Jenkins → New Item → Enter Job Name → Freestyle Project → OK
2. Source Code Management:
   - Choose Git → Enter Repository URL → Add credentials if private
3. Build Triggers:
   - Optional: Poll SCM or Build periodically
4. Build Environment:
   - Optional: Delete workspace before build
5. Build Steps:
   - Invoke top-level Maven targets
   - POM: pom.xml
   - Goals: clean test
6. Post-build Actions:
   - Publish HTML reports (ExtentReports)
   - Archive screenshots/logs if needed

Option 2: Pipeline Project (Recommended)
----------------------------------------
- Pipeline allows full control and parameterization

4️⃣ Add Parameterization for Different Environments
---------------------------------------------------
1. In Jenkins Job → Check "This project is parameterized"
2. Add Parameters:
   - Choice Parameter:
     - Name: ENV
     - Choices: qa, uat, prod
     - Description: Environment to run tests against
   - Choice Parameter:
     - Name: BROWSER
     - Choices: chrome, firefox, edge
     - Description: Browser to run tests on
   - Boolean Parameter (optional):
     - Name: GRID
     - Default: false
     - Description: Run tests on Selenium Grid

3. In your framework, ConfigReader should read:
   - config-${ENV}.properties dynamically

5️⃣ Jenkins Pipeline Script (Jenkinsfile)
-----------------------------------------
pipeline {
    agent any

    tools {
        jdk 'JDK8'          // Name in Jenkins global tools
        maven 'Maven3'      // Name in Jenkins global tools
    }

    parameters {
        choice(name: 'ENV', choices: ['qa','uat','prod'], description: 'Environment to test')
        choice(name: 'BROWSER', choices: ['chrome','firefox','edge'], description: 'Browser to run tests')
        booleanParam(name: 'GRID', defaultValue: false, description: 'Use Selenium Grid?')
    }

    environment {
        // Map Jenkins parameters to environment variables
        BROWSER = "${params.BROWSER}"
        ENV = "${params.ENV}"
        GRID = "${params.GRID}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yourrepo/selenium-framework.git'
            }
        }

        stage('Clean & Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test -Dbrowser=${BROWSER} -Denv=${ENV} -Dgrid=${GRID}"
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Test Report'
                ])
                archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'logs/**/*', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "Build & Tests Successful!"
        }
        failure {
            echo "Build or Tests Failed!"
        }
    }
}

6️⃣ Configure TestNG for Parameterization
-----------------------------------------
1. In your Test class, read Maven system properties:
   String browser = System.getProperty("browser", "chrome");
   String env = System.getProperty("env", "qa");
   boolean grid = Boolean.parseBoolean(System.getProperty("grid", "false"));

2. DriverFactory uses these variables to initialize WebDriver:
   - local vs Grid
   - Chrome/Firefox/Edge
   - URL from config-${env}.properties

3. TestNG XML can also use parameters if needed:
   <parameter name="browser" value="${BROWSER}"/>
   <parameter name="env" value="${ENV}"/>

7️⃣ Optional: Selenium Grid Integration
----------------------------------------
1. Start Grid Hub: java -jar selenium-server-<version>.jar hub
2. Start Nodes:
   java -jar selenium-server-<version>.jar node --hub http://localhost:4444
3. Jenkins GRID parameter = true → DriverFactory uses RemoteWebDriver
4. Run tests in parallel across multiple browsers

8️⃣ Run & Verify
----------------
1. Trigger job manually or via SCM poll
2. Jenkins downloads code, compiles, runs tests
3. Verify:
   - ExtentReports published
   - Screenshots archived
   - Logs archived
4. For different environments, choose ENV and BROWSER in parameters

✅ Notes:
- Always use ThreadLocal<WebDriver> for parallel execution
- Use Maven system properties for dynamic environment configuration
- Archive all reports and logs for traceability
- Optional: Use Slack/email notifications for test results
