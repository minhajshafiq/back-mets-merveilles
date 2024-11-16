Feature: Adding a MainCourse
    As a user
    I want to add a MainCourse

    Scenario: Add a MainCourse
        When I add a MainCourse with the following details
        | name       | price | description | id | menuId |
        | Spaghetti  | 10    | Italian     | 1  | null   |
        Then the MainCourse should be added

