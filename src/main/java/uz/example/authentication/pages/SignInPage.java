package uz.example.authentication.pages;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignInPage extends WebPage {
    private static final long serialVersionUID = 1L;

    /**
     * Construct
     */
    public SignInPage() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param parameters The page parameters
     */
    public SignInPage(final PageParameters parameters) {
        add(new SignInPanel("signInPanel"));
    }
}
