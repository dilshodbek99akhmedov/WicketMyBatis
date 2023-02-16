package uz.example.wicket.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Dilshodbek Akhmedov, Tue 15:08. 07/06/22
 */
public class SingInPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public SingInPage(final PageParameters parameters) {
        add(new Label("message", "Login"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new LoginForm("loginForm"));
    }
}
