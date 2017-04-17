Feature: Sample
#
#  @ui @pageobject
#  Scenario: Should be able to search for a product from the input box
#    Given John is viewing the Etsy landing page
#    When he searches for a product from the input box
#    Then the result should be displayed
#
#  @ui @screenplay
#  Scenario: Should be able to search for a product from the input box (screenplay)
#    Given John is viewing the Etsy landing page (screenplay)
#    When he searches for a product from the input box (screenplay)
#    Then the result should be displayed (screenplay)

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

  @ui @wip
  Scenario: Should be able to search for a product from the icons


