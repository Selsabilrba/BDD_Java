package steps;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegistrationSteps {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @Before
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        page = browser.newPage();
    }

    @After
    public void tearDown() {
        browser.close();
        playwright.close();
    }

    @Given("je suis sur la page d'inscription Campus France")
    public void je_suis_sur_la_page_d_inscription_campus_france() {
        page.navigate("https://www.campusfrance.org/en/user/register");
    }

    @When("je sélectionne le profil {string}")
public void je_selectionne_le_profil(String profile) {
    page.getByRole(
            AriaRole.RADIO,
            new Page.GetByRoleOptions().setName(profile)
    ).check(
            new Locator.CheckOptions().setForce(true)
    );
}

    @When("je renseigne l'email {string}")
    public void je_renseigne_l_email(String email) {
        page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions()
                        .setName("monadresse@domaine.com")
        ).fill(email);
    }

    @Then("je vérifie que {string} est affiché")
    public void je_verifie_que_est_affiche(String texte) {
        assertThat(page.locator("#user-form"))
                .containsText(texte);
    }

    @Then("je vérifie que le bouton {string} est présent")
    public void je_verifie_que_le_bouton_est_present(String bouton) {
        assertThat(
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName(bouton)
                )
        ).isVisible();
    }

  
}