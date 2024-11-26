

Feature: End-to-End Checkout Workflow for SourceDemo Domain

  @CompleteWorkflow
  Scenario: Add products to cart, verify, and complete checkout
    Given User is on the login page
    When User logs in using username and password 
    And User selects two products dynamically sorted by price (low to high)
    Then Verify two products are added to the cart
    And Navigate to the cart and verify product details
    When User enters personal details Proceeds to checkout   
    Then Verify payment information, shipping information, and total amount
    And Complete the order
    And Verify order is placed successfully and logout
    
    
    
    
    

  

    
