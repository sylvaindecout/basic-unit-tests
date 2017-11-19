# basic-unit-tests

This project is a very basic example used to illustrate how unit test frameworks integrate.

The entry point is **PricingService**, which is in charge of providing a price for an article reference.
A **catalogue** provides article details. A **discount policy** decides if/which discount is applicable.

## Build lifecycle

Maven build is customized so that plugins run automatically.

1. **initialize** phase: Jacoco prepares agent for unit tests
2. **test**: Surefire runs unit tests
3. **pre-integration-test**: Jacoco prepares agent for integration tests
4. **integration-test**: Failsafe runs integration tests
4. **integration-test**: Pitest generates report for unit tests
5. **post-integration-test**: Jacoco generates report for integration tests
6. **verify**: Jacoco generates report for unit tests

## Frameworks

This project implements tests with both JUnit4 and Jupiter. Supporting both impacts Maven configuration a lot.

Besides, this support is not complete, as Maven plugin for Pitest does not support Jupiter yet.