package uz.example.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;
import uz.example.db_service.users.User;
import uz.example.db_service.users.UserService;
import uz.example.view.HomePage;

/**
 * @author Dilshodbek Akhmedov, Tue 15:26. 07/06/22
 */
public class LoginForm extends Form<LoginForm> {

    UserService service = new UserService();
    private TextField<String> usernameField;
    private PasswordTextField passwordField;

    public LoginForm(String id) {
        super("loginForm");
        setDefaultModel(new CompoundPropertyModel<>(this));
        add(usernameField = new RequiredTextField<>(
                "username", new Model<>()
        ));
        add(passwordField = new PasswordTextField(
                "password", new Model<>()
        ));
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();

        String username = usernameField.getModelObject();
        String password = passwordField.getModelObject();

        // Just to be safe: make sure password doesn't remain in memory
        passwordField.setModelObject(null);

        if (Strings.isEmpty(username))
            return;

        User user = service.getUser(username);

        if (user != null && user.getPassword().equals(password)) setResponsePage(HomePage.class);
//        else setResponsePage(SingInPage.class);
//        boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
//        //if authentication succeeds redirect user to the requested page
//        if (authResult) {
//            continueToOriginalDestination();
//        }


    }


}
