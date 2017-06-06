Feature:
  As a customer, I want to select the type of car I want to sell,
  so that I can receive an offer from car buyer companies.

  Scenario Outline: user is able to select brands by locale.
    Given the local:<locale> information.
    Then all brands associated to <locale> are available for user.

    Examples:
      | locale |
      | de_DE  |
      | en_UK  |
      | en_US  |

  Scenario Outline: user is able to select models by locale and brand.
    Given the brand:<brand> and local:<locale> information.
    Then all models associated to <brand> and <locale> are available for user.

    Examples:
      | locale | brand   |
      | de_DE  | Bentley |
      | en_UK  | Nissan  |
      | en_US  | Ford    |

  Scenario Outline: user is able to select model years by locale, brand and model.
    Given the model:<model>, brand:<brand> and local:<locale> information.
    Then all model years associated to <model>, <brand> and <locale> are available for user.

    Examples:
      | locale | brand   | model  |
      | de_DE  | Bentley | Azure  |
      | en_UK  | Nissan  | Laurel |
      | en_US  | Ford    | Focus  |

  Scenario Outline: user is able to select a specific car.
    Given the manufacturer/brands are filtered by locale -> <locale>
    When the brand -> <brand> is selected by user
    And the model -> <model> is selected as well
    Then all model years are available in order to user select the specific car.

    Examples:
      | locale | brand   | model  |
      | de_DE  | Bentley | Azure  |
      | en_UK  | Nissan  | Laurel |
      | en_US  | Ford    | Focus  |