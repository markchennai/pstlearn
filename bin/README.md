Thanks for choosing codegen toolkit for starting your microservice journey.

Feature set:
a) Read the OAS API Spec (supported v 3.0+) supplied and has created a maven project

b) Created basic controller based on rest verb and path configured in API speic

c) Generated default unit test case using junit

d) Generated templates for business layer and database layer

e) Generated templates for Dto layer matching with API model

f) Generated Exception handler with i18n support (Try passing Accept-Lanaguage as es)


Steps:
1: Install lombok on your ide if it is not available (https://projectlombok.org/setup/eclipse)
2: Execute `mvn package` to download dependencies and generate lombok and mastruct classes
3: Add Datasource related properties


## Datasource Properties

-DdbServer=<<database server>>

-DdbPort=<<database port>>

-Ddb=<<database name>>

-Dusername=<<database username>>

-Dpassword=<<database password>>


Note: Business layer and DAO layer are template classes where you need to add business logic.
Certain methods may produce error as queries or required components in API spec are not provided
