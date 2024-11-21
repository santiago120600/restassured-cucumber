Feature: Validating Place API's 

Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with Post http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"