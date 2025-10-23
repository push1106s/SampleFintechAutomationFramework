💳 Sample Fintech Automation Framework
A robust automation framework designed to validate core functionalities of a sample fintech application using both UI and API testing. 
Built with Java, Selenium WebDriver, REST-assured, and TestNG, this framework ensures scalable, maintainable, and efficient test coverage across critical user and transaction flows.

🚀 Features
1. UI automation using Selenium WebDriver
2. API automation using REST-assured
3.  Test orchestration with TestNG
4. Page Object Model (POM) for clean UI structure
5. Modular payload generation for API requests
6. Allure reporting integration for rich test insights
7. Environment-based configuration support (QA, Dev, Prod)

🛠️ Technologies Used
Java 11+
Selenium WebDriver
REST-assured
TestNG
Allure Reporting
Maven

⚙️ Setup Instructions

1. Clone Repository git clone https://github.com/push1106s/SampleFintechAutomationFramework.git

2.Import into IDE
Open the project in IntelliJ IDEA or Eclipse.
Ensure Maven is enabled and dependencies are downloaded.

3.Configure Environment
Update resources/global.properties with appropriate URLs and browser settings:
env=qa
browser=Chrome
baseqaurl=https://your-qa-url.com
basedevurl=https://your-dev-url.com
baseprodurl=https://your-prod-url.com

4.Run Tests
a) To run UI tests(for single test suite):
mvn test -DtestsuiteXmlFiles=TestNg/smoke.xml 

b) To run UI tests(to run multiple suite):
mvn test -DtestsuiteXmlFiles=TestNg/smoke.xml,TestNg/regression.xml 

c) To run API tests:
mvn test -Dtest=UserCreationApiTest

📈 Reporting
Allure reports are automatically generated after test execution.
To view the report:allure serve target/allure-results

🧪 Test Coverage
UI Tests
User registration with valid inputs
Transaction creation with valid input
//For now just added two to show the framework structure

API Tests
Create user via API
Get user details
Update user email
Create transaction
Get transaction details
Negative scenarios (unauthorized access, invalid endpoints)
