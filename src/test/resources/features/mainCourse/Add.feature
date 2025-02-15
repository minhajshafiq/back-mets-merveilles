Feature: Adding the menu items
    As a user
    I want to add a Starter, MainCourse, Dessert and Drink

    Scenario: Add a Starter
        When I add a Starter with the following details
            | name        | price | description | id | menuId | imageUrl       |
            | GarlicBread | 5     | Italian     | 1  | null   | test-image.jpg |
        Then the Starter named 'GarlicBread' should be added

    Scenario: Add a MainCourse
        When I add a MainCourse with the following details
            | name       | price | description | id | menuId | imageUrl       |
            | Spaghetti  | 10    | Italian     | 1  | null   | test-image.jpg |
        Then the MainCourse named 'Spaghetti' should be added

    Scenario: Add a Dessert
        When I add a Dessert with the following details
            | name       | price | description | id | menuId | imageUrl       |
            | Tiramisu   | 7     | Italian     | 1  | null   | test-image.jpg |
        Then the Dessert named 'Tiramisu' should be added

    Scenario: Add a Drink
        When I add a Drink with the following details
        | name       | price | description | id | menuId | imageUrl       |
        | Coke       | 2     | Soft Drink  | 1  | null   | test-image.jpg |
        Then the Drink named 'Coke' should be added


#    Scenario: Add a Menu
#        When I add a Menu with the following details
#        | name       | id | restaurantId | imageUrl       |
#        | Italian    | 1  | 1            | test-image.jpg |
#        Then the Menu should be added


