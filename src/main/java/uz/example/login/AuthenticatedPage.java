package uz.example.login;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Dilshodbek Akhmedov, Tue 10:28. 07/06/22
 */
public class AuthenticatedPage extends WebPage {

    @Override
    protected void onConfigure() {
        super.onConfigure();
        AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
        //if user is not signed in, redirect him to sign in page
        if (!AuthenticatedWebSession.get().isSignedIn())
            app.restartResponseAtSignInPage();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new BookmarkablePageLink<Void>("goToHomePage", getApplication().getHomePage()));
        add(new Link<Void>("logOut") {
            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
    }


}