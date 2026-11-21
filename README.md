# AI Agent Response Evaluation

## Project Overview

This project demonstrates automated testing of an AI-agent-style customer support response evaluator using Java 17, Maven, and JUnit 5.

The evaluator checks whether customer support responses follow predefined quality, accuracy, relevance, and security rules.

This is a simulated evaluation project. It does not connect to a live AI service or external AI API.

## Technologies Used

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA

## Evaluation Features

1. Security validation – detects requests for passwords and verification codes.
2. Payment accuracy – detects unsupported payment-success claims.
3. Recovery escalation – checks whether failed account recovery is escalated to support.
4. Response relevance – identifies responses that do not address the customer's question.
5. Response length – enforces a maximum response length of 500 characters.
6. Input validation – handles null and empty inputs.
7. Consistency testing – checks repeatability for identical inputs.

## Testing Techniques

- Positive testing
- Negative testing
- Boundary-value analysis
- Parameterized testing
- Input validation
- Regression testing
- Consistency testing

## Test Execution

Run the following command from the project root:

```bash
mvn clean test

Latest Test Results
- Tests executed: 22
- Passed: 22
- Failed: 0
- Errors: 0
- Skipped: 0
Build status: SUCCESS