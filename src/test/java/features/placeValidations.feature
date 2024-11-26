Feature: Validating Place API's 

Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with '<name>' '<address>' '<types>' '<website>' '<language>' <accuracy> <latitude> <longitude>
    When User calls "addPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And verify place_id created maps to '<name>' using "getPlaceAPI"

    Examples:
      | name              | address            | types | website           | language | accuracy | latitude | longitude |
      | Canton del santi  | Melchor ocampo #42 | shop  | http://google.com | Spanish  |       50 |    -35.0 |     151.0 |
      | Canton del santi1 | Melchor ocampo #42 | shop  | http://google.com | Spanish  |       50 |    -35.0 |     151.0 |

Scenario Outline: Verify if Place is being Successfully deleted using deletePlaceAPI
    Given Delete Place Payload
    When User calls "deletePlaceAPI" with "Delete" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"

