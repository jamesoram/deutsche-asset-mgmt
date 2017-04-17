Feature: Sample

  @ui @pageobject
  Scenario: Should be able to search for a product from the input box
    Given John is viewing the Etsy landing page
    When he searches for a product from the input box
    Then the result should be displayed

  @ui @screenplay
  Scenario: Should be able to search for a product from the input box (screenplay)
    Given John is viewing the Etsy landing page (screenplay)
    When he searches for a product from the input box (screenplay)
    Then the result should be displayed (screenplay)

  # There are many ways for a user to search from the drop-down.
  # I have chosen the one that I would use - in this case search for "craft"
  # in the top menu and then search for "needlecraft" in one of the sub-menus.
  # Another option would be to navigate all the menus searching for the word "craft"
  # or perhaps something with more clicks. I think it would be good to clarify exactly what
  # you are expecting the candidate to do and how reusable it needs to be.
  @ui @pageobject
  Scenario: Should be able to search for a product from the drop-down menu
    Given John is viewing the Etsy landing page
    When he searches for a product from the drop-down menu
    Then the chosen category should be displayed

  @ui @pageobject
  Scenario: Should be able to search for a product from the icons
    Given John is viewing the Etsy landing page
    When he searches for a product from an icon
    Then the chosen category should be displayed

  @api
  Scenario: doing a GET to /search with one parameter should return correct results
    Given an API client
    When it does a GET to /search with the parameter "craft"
    Then the results contain the word "craft"

  @api
  Scenario: doing a GET to /search with more than one parameter should return correct results
    Given an API client
    When it does a GET to /search with the parameters "hand" and "craft"
    Then the results contain the words "hand" and "craft"

  # an invalid parameter could be one which is never going to exist or possibly a swear word which should
  # not be in autosuggest and should not return results
  @api
  Scenario: doing a GET to /search with an invalid parameter does not return results
    Given an API client
    When it does a GET to /search with an invalid keyword
    Then no results are returned

  # api tests could be created for 3rd party software that integrates with Etsy
  # we would always want to test for happy paths and potential failure cases.