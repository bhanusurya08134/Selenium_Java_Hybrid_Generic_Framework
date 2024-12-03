Feature: Provider Selection

@Provider_selection @Scheme
Scenario: Create and Update Scheme

Given I hit Scheme POST with name as "AutoTestScheme00123"
Then I verify the status code as "200"
And I validate the response for Scheme POST

When I hit Scheme GetALL
Then I verify the status code as "200"
And I validate the response for Scheme GetALL

When I hit Scheme GetID
Then I verify the status code as "200"
And I validate the response for Scheme GetID

Given I hit Scheme PUT
Then I verify the status code as "200"
And I validate the response for Scheme PUT

When I hit Scheme GetALL
Then I verify the status code as "200"
And I validate the response for Scheme GetALL

When I hit Scheme GetID
Then I verify the status code as "200"
And I validate the response for Scheme GetID


@Provider_selection @User
Scenario: Create and Update User

Given I hit User POST with name as "www.TestAutotest0081.com"
Then I verify the status code as "200"
And I validate the response for User POST

When I hit User GetALL
Then I verify the status code as "200"
And I validate the response for User GetALL

When I hit User GetID
Then I verify the status code as "200"
And I validate the response for User GetID

Given I hit User PUT
Then I verify the status code as "200"
And I validate the response for User PUT


When I hit User GetALL
Then I verify the status code as "200"
And I validate the response for User GetALL

When I hit User GetID
Then I verify the status code as "200"
And I validate the response for User GetID


@Provider_selection @HttpApiMethod
Scenario: Create and Update HttpApiMethod

Given I hit HttpApiMethod POST with name as "TestAutoMethod007"
Then I verify the status code as "200"
And I validate the response for HttpApiMethod POST

When I hit HttpApiMethod GetALL
Then I verify the status code as "200"
And I validate the response for HttpApiMethod GetALL

When I hit HttpApiMethod GetID
Then I verify the status code as "200"
And I validate the response for HttpApiMethod GetID

Given I hit HttpApiMethod PUT
Then I verify the status code as "200"
And I validate the response for HttpApiMethod PUT

When I hit HttpApiMethod GetALL
Then I verify the status code as "200"
And I validate the response for HttpApiMethod GetALL

When I hit HttpApiMethod GetID
Then I verify the status code as "200"
And I validate the response for HttpApiMethod GetID


@Provider_selection @Department
Scenario: Create and Update Department

Given I hit Department POST with name as "Stephen"
Then I verify the status code as "200"
And I validate the response for Department POST

When I hit Department GetALL
Then I verify the status code as "200"
And I validate the response for Department GetALL

When I hit Department GetID
Then I verify the status code as "200"
And I validate the response for Department GetID

Given I hit Department PUT
Then I verify the status code as "200"
And I validate the response for Department PUT

When I hit Department GetALL
Then I verify the status code as "200"
And I validate the response for Department GetALL

When I hit Department GetID
Then I verify the status code as "200"
And I validate the response for Department GetID


@Provider_selection @Auth
   Scenario Outline: Create and Update Auth
   
    Given I hit Auth POST with name as "TestAuth_Auto007"
    Then I verify the status code as "200"
    And I validate the response for Auth POST 
    
    When I hit Auth GetALL
    Then I verify the status code as "200"
    And I validate the response for Auth GetALL
    
    When I hit Auth GetID
    Then I verify the status code as "200"
    And I validate the response for Auth GetID
    
    When I hit Auth PUT
    Then I verify the status code as "200"
    And I validate the response for Auth PUT
    
    When I hit Auth GetALL
    Then I verify the status code as "200"
    And I validate the response for Auth GetALL
    
    When I hit Auth GetID
    Then I verify the status code as "200"
    And I validate the response for Auth GetID


@Provider_selection @Source
Scenario: Create and Update Source

Given I hit Source POST with name as "AutoTestSource007"
Then I verify the status code as "200"
And I validate the response for Source POST

When I hit Source GetALL
Then I verify the status code as "200"
And I validate the response for Source GetALL

When I hit Source GetID
Then I verify the status code as "200"
And I validate the response for Source GetID

Given I hit Source PUT
Then I verify the status code as "200"
And I validate the response for Source PUT

When I hit Source GetALL
Then I verify the status code as "200"
And I validate the response for Source GetALL

When I hit Source GetID
Then I verify the status code as "200"
And I validate the response for Source GetID


@Provider_selection @BasicAuth
Scenario: Create and Update BasicAuth

Given I hit BasicAuth POST with name as "AutoTest008"
Then I verify the status code as "200"
And I validate the response for BasicAuth POST

When I hit BasicAuth GetALL
Then I verify the status code as "200"
And I validate the response for BasicAuth GetALL

When I hit BasicAuth GetID
Then I verify the status code as "200"
And I validate the response for BasicAuth GetID

Given I hit BasicAuth PUT
Then I verify the status code as "200"
And I validate the response for BasicAuth PUT

When I hit BasicAuth GetALL
Then I verify the status code as "200"
And I validate the response for BasicAuth GetALL

When I hit BasicAuth GetID
Then I verify the status code as "200"
And I validate the response for BasicAuth GetID


@Provider_selection @BearerAuth
Scenario: Create and Update BearerAuth

Given I hit BearerAuth POST with token name as "abcdefghijkl"
Then I verify the status code as "200"
And I validate the response for BearerAuth POST

When I hit BearerAuth GetALL
Then I verify the status code as "200"
And I validate the response for BearerAuth GetALL

When I hit BearerAuth GetID
Then I verify the status code as "200"
And I validate the response for BearerAuth GetID

Given I hit BearerAuth PUT
Then I verify the status code as "200"
And I validate the response for BearerAuth PUT

When I hit BearerAuth GetALL
Then I verify the status code as "200"
And I validate the response for BearerAuth GetALL

When I hit BearerAuth GetID
Then I verify the status code as "200"
And I validate the response for BearerAuth GetID


@Provider_selection @CustomAuth
Scenario: Create and Update CustomAuth

Given I hit CustomAuth POST with name as "AutoTest007"
Then I verify the status code as "200"
And I validate the response for CustomAuth POST

When I hit CustomAuth GetALL
Then I verify the status code as "200"
And I validate the response for CustomAuth GetALL

When I hit CustomAuth GetID
Then I verify the status code as "200"
And I validate the response for CustomAuth GetID

Given I hit CustomAuth PUT
Then I verify the status code as "200"
And I validate the response for CustomAuth PUT

When I hit CustomAuth GetALL
Then I verify the status code as "200"
And I validate the response for CustomAuth GetALL

When I hit CustomAuth GetID
Then I verify the status code as "200"
And I validate the response for CustomAuth GetID


@Provider_selection @Employee
Scenario: Create and Update Employee

Given I hit Employee POST with name as "TestUser007"
Then I verify the status code as "200"
And I validate the response for Employee POST

When I hit Employee GetALL
Then I verify the status code as "200"
And I validate the response for Employee GetALL

When I hit Employee GetID
Then I verify the status code as "200"
And I validate the response for Employee GetID

Given I hit Employee PUT
Then I verify the status code as "200"
And I validate the response for Employee PUT

When I hit Employee GetALL
Then I verify the status code as "200"
And I validate the response for Employee GetALL

When I hit Employee GetID
Then I verify the status code as "200"
And I validate the response for Employee GetID


@Provider_selection @Destination
Scenario: Create and Update Destination

Given I hit Destination POST with name as "http://www.google.com"
Then I verify the status code as "200"
And I validate the response for Destination POST

When I hit Destination GetALL
Then I verify the status code as "200"
And I validate the response for Destination GetALL

When I hit Destination GetID
Then I verify the status code as "200"
And I validate the response for Destination GetID

Given I hit Destination PUT
Then I verify the status code as "200"
And I validate the response for Destination PUT

When I hit Destination GetALL
Then I verify the status code as "200"
And I validate the response for Destination GetALL

When I hit Destination GetID
Then I verify the status code as "200"
And I validate the response for Destination GetID


####################################


@Provider_selection @BasicAuth
Scenario: Delete BasicAuth

When I hit the BasicAuth Delete
Then I verify the status code as "200"

When I hit BasicAuth GetALL
Then I verify the status code as "200"
And I validate the response for BasicAuth GetALL after delete

When I hit BasicAuth GetID
Then I verify the status code as "200"
And I validate the response for BasicAuth GetID after delete


@Provider_selection @BearerAuth
Scenario: Delete BearerAuth

When I hit the BearerAuth Delete
Then I verify the status code as "200"

When I hit BearerAuth GetALL
Then I verify the status code as "200"
And I validate the response for BearerAuth GetALL after delete

When I hit BearerAuth GetID
Then I verify the status code as "200"
And I validate the response for BearerAuth GetID after delete


@Provider_selection @CustomAuth
Scenario: Delete CustomAuth

When I hit the CustomAuth Delete
Then I verify the status code as "200"

When I hit CustomAuth GetALL
Then I verify the status code as "200"
And I validate the response for CustomAuth GetALL after delete

When I hit CustomAuth GetID
Then I verify the status code as "200"
And I validate the response for CustomAuth GetID after delete


@Provider_selection @Employee
Scenario: Delete Employee

When I hit the Employee Delete
Then I verify the status code as "200"

When I hit Employee GetALL
Then I verify the status code as "200"
And I validate the response for Employee GetALL after delete

When I hit Employee GetID
Then I verify the status code as "200"
And I validate the response for Employee GetID after delete


@Provider_selection @Destination
Scenario: Delete Destination

When I hit the Destination Delete
Then I verify the status code as "200"

When I hit Destination GetALL
Then I verify the status code as "200"
And I validate the response for Destination GetALL after delete

When I hit Destination GetID
Then I verify the status code as "200"
And I validate the response for Destination GetID after delete


@Provider_selection @Source
Scenario: Delete Source

When I hit the created Destination Delete
Then I verify the status code as "200"
Then I hit the updated Destination Delete
And I verify the status code as "200"

When I hit the Source Delete
Then I verify the status code as "200"

When I hit Source GetALL
Then I verify the status code as "200"
And I validate the response for Source GetALL after delete

When I hit Source GetID
Then I verify the status code as "200"
And I validate the response for Source GetID after delete


@Provider_selection @Auth
  Scenario: Delete Auth
  
    When I hit the Auth Delete
    Then I verify the status code as "200"
    
    When I hit Auth GetALL
    Then I verify the status code as "200"
    And I validate the response for Auth GetALL after delete
    
    When I hit Auth GetID
    Then I verify the status code as "200"
    And I validate the response for Auth GetID after delete
    
    
@Provider_selection @Scheme
Scenario: Delete Scheme

When I hit the Scheme Delete
Then I verify the status code as "200"

When I hit Scheme GetALL
Then I verify the status code as "200"
And I validate the response for Scheme GetALL after delete

When I hit Scheme GetID
Then I verify the status code as "200"
And I validate the response for Scheme GetID after delete


@Provider_selection @User
Scenario: Delete User

When I hit the User Delete
Then I verify the status code as "200"

When I hit User GetALL
Then I verify the status code as "200"
And I validate the response for User GetALL after delete

When I hit User GetID
Then I verify the status code as "200"
And I validate the response for User GetID after delete


@Provider_selection @HttpApiMethod
Scenario: Delete HttpApiMethod

When I hit the HttpApiMethod Delete
Then I verify the status code as "200"

When I hit HttpApiMethod GetALL
Then I verify the status code as "200"
And I validate the response for HttpApiMethod GetALL after delete

When I hit HttpApiMethod GetID
Then I verify the status code as "200"
And I validate the response for HttpApiMethod GetID after delete


@Provider_selection @Department
Scenario: Delete Department

When I hit the created Employee Delete
Then I verify the status code as "200"
Then I hit the updated Employee Delete
And I verify the status code as "200"

When I hit the Department Delete
Then I verify the status code as "200"

When I hit Department GetALL
Then I verify the status code as "200"
And I validate the response for Department GetALL after delete

When I hit Department GetID
Then I verify the status code as "200"
And I validate the response for Department GetID after delete



 
 
