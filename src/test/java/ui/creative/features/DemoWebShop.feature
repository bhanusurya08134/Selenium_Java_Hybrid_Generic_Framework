##################################################################################################
# Author : Bhanu
# Test Case ID: DWS_01, DWS_02, DWS_03, DWS_04, DWS_05, DWS_06, DWS_07, DWS_08, DWS_09, DWS_010
##################################################################################################
@test
Feature: Demo Webshop application

  @Profile @DWS_01
  Scenario Outline: DWS_01 Validate community pool
    Given I open the Demo Web Shop application
    Then I validate the community pool
    And I close the browser

  @Purchase @DWS_02
  Scenario Outline: DWS_02 Order text book
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to Books page
    And I select book and add to cart "<TCID2>"
    Then I order the book "<TCID2>"
    And I close the browser

    Examples: 
      | TCID2     |
      | DWS_02_01 |

  @Purchase @DWS_03
  Scenario Outline: DWS_03 Order Computer
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to Computers page
    And I select Computer and add to cart "<TCID3>"
    Then I order the Computer "<TCID3>"
    And I close the browser

    Examples: 
      | TCID3     |
      | DWS_03_01 |

  @Profile @DWS_04
  Scenario Outline: DWS_04 Validate customer details
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to My account page
    And I validate the Customer information "<TCID4>"
    And I close the browser

    Examples: 
      | TCID4    |
      | DWS_02_01 |

  @Purchase @DWS_05
  Scenario Outline: DWS_05 Search Computing and internet
    Given I login to Demo Web Shop application with valid credentials
    And I search and validate Computing and internet "<TCID5>"
    And I close the browser

    Examples: 
      | TCID5     |
      | DWS_05_01 |

  #| DWS_05_02 |
  #| DWS_05_03 |
  #| DWS_05_04 |
  @Review @DWS_06
  Scenario Outline: DWS_06 Validate email message
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to Jewelry page
    And I select Jewel and add to wish list with testdata "<TCID6>"
    Then I navigate to Wishlist page
    And I validate Jewel in wish list with testdata "<TCID6>"
    And I send mail to friend Email about product with testdata "<TCID6>"
    And I close the browser

    Examples: 
      | TCID6     |
      | DWS_06_01 |

  @Review @DWS_07
  Scenario Outline: DWS_07 Perform a Product Review
    Given I login to Demo Web Shop application with valid credentials
    Then I search a product with testData "<TCID7>"
    And I click on Add review and write and submit review with testdata "<TCID7>"
    Then I validate submitted review with testdata "<TCID7>"
    And I close the browser

    Examples: 
      | TCID7     |
      | DWS_07_01 |

  @Profile @DWS_08
  Scenario Outline: DWS_08 View and Edit User Profile
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to My account page
    Then I Edit the User Profile in my account page With testData "<TCID8>"
    Then I Validate the User Profile changes are saved successfully With testData "<TCID8>"
    And I close the browser

    Examples: 
      | TCID8     |
      | DWS_08_01 |

  @Purchase @DWS_09
  Scenario Outline: DWS_09 Remove Product from Cart
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to Books page
    Then I select the Multiple Products With testData "<TCID9>"
    Then I Remove any one of the product in shopping cart page With testData "<TCID9>"
    Then I verify the Product Is removed in shopping cart page With testData "<TCID9>"
    And I close the browser

    Examples: 
      | TCID9     |
      | DWS_09_01 |

  @Review @DWS_10
  Scenario Outline: DWS_10 Sort Products by Price
    Given I login to Demo Web Shop application with valid credentials
    Then I navigate to Jewelry page
    Then I Select the Sorting Option Price Low to High
    Then I Select the Sorting Option Price High to Low
    Then I Select the Sorting Option Name A to Z
    Then I Select the Sorting Option Name Z to A
    And I close the browser
