Feature: XML Upload


@XML_upload
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


@XML_upload
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


@XML_upload
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


####################################


@XML_upload
Scenario: Delete Employee

When I hit the Employee Delete
Then I verify the status code as "200"

When I hit Employee GetALL
Then I verify the status code as "200"
And I validate the response for Employee GetALL after delete

When I hit Employee GetID
Then I verify the status code as "200"
And I validate the response for Employee GetID after delete


@XML_upload
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


@XML_upload
Scenario: Delete User

When I hit the User Delete
Then I verify the status code as "200"

When I hit User GetALL
Then I verify the status code as "200"
And I validate the response for User GetALL after delete

When I hit User GetID
Then I verify the status code as "200"
And I validate the response for User GetID after delete
 
 
