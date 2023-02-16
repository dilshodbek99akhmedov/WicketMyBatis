package uz.example;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Bytes;
import uz.example.wicket.login.BasicAuthenticationSession;
import uz.example.wicket.login.SingInPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication {
    /**
     * Constructor
     */
    public WicketApplication() {
    }

    public Class<? extends WebPage> getHomePage() {
        return SingInPage.class;
    }

    @Override
    protected void init() {
        super.init();

        setRootRequestMapper(new CryptoMapper(getRootRequestMapper(), this));

        getApplicationSettings().setDefaultMaximumUploadSize(Bytes.kilobytes(10240));

        mountPage("login", SingInPage.class);

//        HttpSession session = ((ServletWebRequest) RequestCycle.get()
//                .getRequest()).getContainerRequest().getSession();
//
//        ISessionListener myListener = null;
//        //add a custom session listener
//        getSessionListeners().add(myListener);


//        getMarkupSettings().setMarkupIdGenerator(new DefaultMarkupIdGenerator());

        getCspSettings().blocking()
//                .add(CSPDirective.IMG_SRC, "data:")
//                .add(CSPDirective.FONT_SRC, "https://maxcdn.bootstrapcdn.com")
                .add(CSPDirective.STYLE_SRC,
                        "https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css", "src/main/webapp/css/main.css")
                .add(CSPDirective.SCRIPT_SRC,
                        "https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js")
                .add(CSPDirective.STYLE_SRC, "css/edit-page.css")
                .add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF);


        getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "css_files"));


//        getCspSettings().blocking().disabled();
//        getResourceSettings().setCssCompressor(new CssUrlReplacer());
//        getResourceSettings().setThrowExceptionOnMissingResource(true);

    }

    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return BasicAuthenticationSession.class;
    }


//    public Class<? extends WebPage> getHomePage() {
//        return HomePage.class;
//    }
//
//    @Override
//    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
//        return BasicAuthenticationSession.class;
//    }
//
//    @Override
//    protected Class<? extends WebPage> getSignInPageClass() {
//        return SignInPage.class;
//    }
//
//    @Override
//    public void init() {
//        getSecuritySettings().setAuthorizationStrategy(new MetaDataRoleAuthorizationStrategy(this));
//        MetaDataRoleAuthorizationStrategy.authorize(AdminOnlyPage.class, Roles.ADMIN);
//    }
}
