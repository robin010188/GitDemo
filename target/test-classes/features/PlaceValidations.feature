Feature: Validating place APIs 

@AddPlace
Scenario Outline: Verify if place is being successfully added 
    Given Add place payload "<name>" "<address>"  "<language>"
     When user calls "AddPlace" API with "POST" HTTP request 
     Then the API call got success with Status Code 200 
      And "status" in response body is "OK" 
      And "scope" in response body is "APP" 
      And verify place_id created maps to "<name>" using "GetPlace" API
  
    Examples: 
      | name          | address                | language | 
      | Robin Deshwal | 7727 S Garden Manor Dr | Spanish  | 
      | Mithun        | 7727 N swimin Manor Dr | English  | 
  
@DeletePlace
Scenario: Verify if Delete place functionality is working 
    Given Delete place payload
     When user calls "DeletePlace" API with "POST" HTTP request 
     Then the API call got success with Status Code 200 
      And "status" in response body is "OK" 

