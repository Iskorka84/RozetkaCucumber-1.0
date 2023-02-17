Feature: Pagination on Rozetka

 Scenario Outline: Successful api request
    Given I set "<URL>" for request
    When I send GET http request to URL
    Then I receive valid http response code 200
   Examples:
   |URL|
   |https://api-analytics.rozetka.com.ua/bulk|

 Scenario Outline: Moving by page numbers
    Given I start my browser
    And I load network equipment page
    And I scroll down to pagination area
    When I click on link with "<page_number>"
    Then Page number "<page_number>" is loaded
   Examples:
   |page_number|
   |5          |

