Automation Testing Framework – AutomationExercise:

A complete Selenium + TestNG automation testing framework developed as part of the Edges Software Testing Diploma.
This project automates 26 real-world web test scenarios on AutomationExercise.com
, including user registration, login, checkout, and more.

Features:

Selenium WebDriver with TestNG integration

Page Object Model (POM) for clean and reusable test design

Allure Reporting for rich HTML test reports

Retry logic for flaky tests

Cross-browser support (Chrome by default)

Automatic screenshots on test success or failure

Project Structure:
src/
 ├─ main/java/framework/       # Base classes (DriverFactory, BasePage, Utils)
 ├─ main/java/pages/           # Page Object Models
 └─ test/java/tests/           # Test classes organized by scenario
pom.xml                        # Maven dependencies
testng.xml                     # Test suite configuration

Tools & Technologies:

Java

Selenium WebDriver

TestNG

Maven

Allure Reporting

Git & GitHub

How to Run Tests:

Clone the repository:

git clone https://github.com/YOUR_USERNAME/automation-testing-framework.git


Navigate to the project directory:

cd automation-testing-framework


Run the test suite via Maven:

mvn clean test


Generate and open Allure report:

allure serve allure-results

Author:

Mohamed Sameh Mahmoud
mohamed.sameh.8700@gmail.com


This framework represents the culmination of my learning in software testing and automation, showcasing hands-on experience with professional testing tools and design principles.
