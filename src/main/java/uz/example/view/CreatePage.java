package uz.example.view;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import uz.example.db_service.users.User;
import uz.example.db_service.users.UserService;


/**
 * @author Dilshodbek Akhmedov, Mon 09:50. 06/06/22
 */
public class CreatePage extends WebPage {

    UserService service = new UserService();
    private TextField name;
    private TextField age;
    private TextField username;
    private PasswordTextField password;

    private FileUploadField fileUploadField;

    public CreatePage(PageParameters parameters) {
        super();
        add(new Label("hello", "Hello World!"));

        Form form = new Form<>("form");

        name = new TextField("name", new Model(""));
        form.add(name);

        age = new TextField("age", new Model(""));
        form.add(age);

        username = new TextField("username", new Model(""));
        form.add(username);

        password = new PasswordTextField("password", new Model(""));
        form.add(password);

        fileUploadField = new FileUploadField("fileUploadField");
//        form.setMaxSize(Bytes.megabytes(10));
        form.add(fileUploadField);

        form.add(new Button("save") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                User user = new User(name.getModelObject().toString(), age.getModelObject().toString(), username.getModelObject().toString(), password.getModelObject());
                boolean b = service.create(user);
                System.out.println("fileUploadField = " + fileUploadField.getFileUpload().getClientFileName());
                if (b) setResponsePage(HomePage.class);
            }
        });

        form.add(new Button("back") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                setResponsePage(HomePage.class);
            }
        });

        add(form);
    }
}