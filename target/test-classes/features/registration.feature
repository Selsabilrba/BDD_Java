Feature: Inscription Campus France

Scenario Outline: Parcours d'inscription
    Given je suis sur la page d'inscription Campus France
    When je sélectionne le profil "<profile>"
    And je renseigne l'email "<email>"
    Then je vérifie que "Informations complémentaires" est affiché
    And je vérifie que le bouton "CREATE AN ACCOUNT" est présent
 

    Examples:
      | profile    | email                        |
      | Students   | student.test@example.com     |
      | Researcher | researcher.test@example.com  |