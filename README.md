# Price calculation API

## Project

### Overview
You’re implementing a part of a shopping platform. Design and implement a service that will provide a REST API for calculating a given product's price and amount. Products in the system are identified by UUID. There should be the possibility of applying discounts based on two policies – count based (the more pieces of the product are ordered, the bigger the discount is) and percentage based. Policies should be configurable. Non-functional requirements

- Use Java >= 8 and frameworks of your choice
- The project should be containerized and easy to build and run via Gradle or Maven.
- Please provide a README file with instructions on how to launch it
- There's no need for full test coverage. Implement only the most essential (in your opinion) tests
- Use the git repository for developing the project and after you’re done, send us a link to it
- Make sure we can run the project easily, without any unnecessary local dependencies (e.g., Don’t use OS-specific code)

### Analysis
#### Price calculation
From above description (`calculating a given product's price and amount`) it is hard to determine what and how should be calculated. The most possible scenario could be one of
- calculate product's total price based on given product and amount
- calculate product's total price based on product's price and amount

In either case there is possibility to apply discount based on given strategy.

#### Calculating discount
- It is not defined how discount should be calculated based on amount strategy

### Assumptions and decisions
Given the nature of this task and lack of possibility to clarify requirements, following assumption and decisions  has been made:

#### Price calculation

Service will allow to calculate price either based on product id and amount or product price and amount.
Discount will be applied based on provided discount policy and parameters.

#### Percentage discount
Percentage discount will reduce total price of all products. Possible percentage value range would be 0-100 (including 0 and 100).

total price = (100 - discount_percentage) * (amount * price_of_product)

#### Amount discount
Amount based discount strategy will add percentage discount that will be calculated based on configurable base discount that will be multiplied based on amount products over 1 piece.

discount_percentage = (amount - 1) * discount_step
total price = (100 - discount_percentage) * (amount * price_of_product)

# Implementation

## Requirements

- java 17
- docker

## Running application

### Building

1. Set JAVA_HOME (if not set)
2. Build application image

```bash
./gradlew bootBuildImage
```

3. Run application with docker

```bash
docker run -p 8080:8080 price-api:0.0.1-SNAPSHOT
```

### Swagger

Service exposes swagger that can be accessed via http://localhost:8080/swagger-ui/index.html after starting application
locally

### Local product store

For demonstration purposes product price lookup is done based on in memory implementation that following products:

- id: `39a8ce44-13a5-4fd6-a6e7-cbe08a2e8ec7` price: 1
- id: `4eb94e83-ecd0-49c4-aee8-17b023f466dc` price: 10
- id: `f6fbc666-9814-40ca-b68e-0889fa970621` price: 100

## Possible improvements and changes to consider
### Multiple price calculation/discount application policies
Currently there is single price calculation strategy for calculating initial price and applying discount.
It might be worth to consider adding possibility to apply different price calculation strategies or different discount application strategies. 

It might be achieved by e.g. introducing additional price calculation strategies alongside discount calculation strategies 
or merging price and discount calculation as a single operation
