Feature: Adding a MainCourse
    As a user
    I want to add a MainCourse

    Scenario: Add a Starter
        When I add a Starter with the following details
            | name       | price | description | id | menuId |
            | GarlicBread| 5     | Italian     | 1  | null   |
        Then the Starter should be added

    Scenario: Add a MainCourse
        When I add a MainCourse with the following details
        | name       | price | description | id | menuId |
        | Spaghetti  | 10    | Italian     | 1  | null   |
        Then the MainCourse named {string} should be added

    Scenario: Add a Desserts
        When I add a Desserts with the following details
        | name       | price | description | id | menuId |
        | Tiramisu   | 7     | Italian     | 1  | null   |
        Then the Dessert should be added

    Scenario: Add a Drink
        When I add a Drink with the following details
        | name       | price | description | id | menuId |
        | Coke       | 2     | Soft Drink  | 1  | null   |
        Then the Drink should be added

    Scenario: Add a Menu
        When I add a Menu with the following details
        | name       | id | restaurantId |
        | Italian    | 1  | 1            |
        Then the Menu should be added


