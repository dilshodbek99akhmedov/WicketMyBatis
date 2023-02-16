package uz.example.login;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import uz.example.db_service.users.User;
import uz.example.db_service.users.UserService;

/**
 * @author Dilshodbek Akhmedov, Tue 10:30. 07/06/22
 */

public class BasicAuthenticationSession extends AuthenticatedWebSession {

    UserService service = new UserService();

    public BasicAuthenticationSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        //user is authenticated if both username and password are equal to 'wicketer'
        User user = service.getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public Roles getRoles() {
        return new Roles();
    }
}
