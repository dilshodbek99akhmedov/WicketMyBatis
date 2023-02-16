package uz.example.authentication.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignOutPage extends WebPage {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     */
    public SignOutPage() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param parameters Page parameters (ignored since this is the home page)
     */
    public SignOutPage(final PageParameters parameters) {
        getSession().invalidate();
    }
}
