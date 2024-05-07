# Getting Started

### Build and Run

To start the service use
`mvn clean spring-boot:run`

URL to retrieve account balance, use with GET
http://localhost:8080/api/accounts/12345678

URL to make transfer, use with POST
http://localhost:8080/api/transfers/
sample payload:
`{
"fromAccount": "12345678",
"toAccount": "88888888",
"amount": 1000.00
}`

### Notes
The db file is committed, it is not a usual practise.
But since the requirement to enable file mode H2 and keep the data.
To avoid you need to run the sql script to create the accounts on startup, so I committed the db file also.

Although it did not include actuator, but the service is put under /api/ to separate from health endpoints
from actuators if this is used.

For better tracing, it can use spring cloud sleuth, so each request could have a traceId.
By including the traceId/spanId in our log lines, it can provide more information to the
log aggregator (e.g. Splunk / Elastic Search) to search for logs content relating to a specific request

To keep it simple and relevant, this sample did not create log files.
We can add appropriate application settings and make use of the @Slf4j annotation
from Lombok to add logger support.

It assumes account number is 8 digit in length. The application return HTTP 400 Bad Request
if the input account number does not fulfill. It is for reference, there shall be discussion
about the error and validation behaviour that the development and business team agrees to.

