Feature: DLL Upload


@DLL_Upload
Scenario: Create and Update DepartmentService

Given I hit DepartmentService POST "<TCID>"
Then I verify the status code as  "200"
And I validate the response for DepartmentService POST

When I hit DepartmentService GetALL
Then I verify the status code as  "200"
And I validate the response for DepartmentService GetALL

When I hit DepartmentService GetID
Then I verify the status code as  "200"
And I validate the response for DepartmentService GetID

Given I hit DepartmentService PUT
Then I verify the status code as  "200"
And I validate the response for DepartmentService PUT

When I hit DepartmentService GetALL
Then I verify the status code as  "200"
And I validate the response for DepartmentService GetALL

When I hit DepartmentService GetID
Then I verify the status code as  "200"
And I validate the response for DepartmentService GetID

Examples: 
|       TCID             |
| TCID_DepartMentService |



@DLL_Upload
Scenario: Create and Update EmployeeService

Given I hit EmployeeService POST "<TCID>"
Then I verify the status code as  "200"
And I validate the response for EmployeeService POST

When I hit EmployeeService GetALL
Then I verify the status code as  "200"
And I validate the response for EmployeeService GetALL

When I hit EmployeeService GetID
Then I verify the status code as  "200"
And I validate the response for EmployeeService GetID

Given I hit EmployeeService PUT
Then I verify the status code as  "200"
And I validate the response for EmployeeService PUT

When I hit EmployeeService GetALL
Then I verify the status code as  "200"
And I validate the response for EmployeeService GetALL

When I hit EmployeeService GetID
Then I verify the status code as  "200"
And I validate the response for EmployeeService GetID

Examples: 
|       TCID             |
| TCID_EmployeeService   |


@DLL_Upload
Scenario: Create and Update UserService

Given I hit UserService POST "<TCID>" 
Then I verify the status code as  "200"
And I validate the response for UserService POST

When I hit UserService GetALL
Then I verify the status code as  "200"
And I validate the response for UserService GetALL

When I hit UserService GetID
Then I verify the status code as  "200"
And I validate the response for UserService GetID

Given I hit UserService PUT
Then I verify the status code as  "200"
And I validate the response for UserService PUT


When I hit UserService GetALL
Then I verify the status code as  "200"
And I validate the response for UserService GetALL

When I hit UserService GetID
Then I verify the status code as  "200"
And I validate the response for UserService GetID

Examples: 
|       TCID         |
| TCID_UserService   |


####################################


@DLL_Upload
Scenario: Delete EmployeeService

When I hit the EmployeeService Delete
Then I verify the status code as  "200"

When I hit EmployeeService GetALL
Then I verify the status code as  "200"
And I validate the response for EmployeeService GetALL after delete

When I hit EmployeeService GetID
Then I verify the status code as  "200"
And I validate the response for EmployeeService GetID after delete


@DLL_Upload
Scenario: Delete DepartmentService

When I hit the created EmployeeService Delete
Then I verify the status code as  "200"
Then I hit the updated EmployeeService Delete
And I verify the status code as  "200"

When I hit the DepartmentService Delete
Then I verify the status code as  "200"

When I hit DepartmentService GetALL
Then I verify the status code as  "200"
And I validate the response for DepartmentService GetALL after delete

When I hit DepartmentService GetID
Then I verify the status code as  "200"
And I validate the response for DepartmentService GetID after delete


@DLL_Upload
Scenario: Delete UserService

When I hit the UserService Delete
Then I verify the status code as  "200"

When I hit UserService GetALL
Then I verify the status code as  "200"
And I validate the response for UserService GetALL after delete

When I hit UserService GetID
Then I verify the status code as  "200"
And I validate the response for UserService GetID after delete
 
 
