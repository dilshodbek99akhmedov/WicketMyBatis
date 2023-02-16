package uz.example.wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import uz.example.dao.User;
import uz.example.service.UserService;

import java.io.Serializable;

/**
 * @author Dilshodbek Akhmedov, Fri 11:31. 03/06/22
 */
public class EditPage extends WebPage {

    UserService service = new UserService();

    public EditPage(User user) {
        super();

        FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);
        feedback.setOutputMarkupId(true);

        add(new Label("hello", "Hello World!"));

        Form form = new Form<ModelClass>("form", new Model(new ModelClass()));

        TextField name = new TextField("name", new PropertyModel(user, "name"));
        form.add(name);

        TextField age = new TextField("age", new PropertyModel(user, "age"));
        form.add(age);

        TextField username = new TextField("username", new PropertyModel(user, "username"));
        form.add(username);

        PasswordTextField password = new PasswordTextField("password", new PropertyModel(user, "password"));
        form.add(password);

        form.add(new AjaxButton("update") {

            @Override
            public void onSubmit(AjaxRequestTarget target) {
                super.onSubmit();
                try {
                    ModelClass modelForm = (ModelClass) getForm().getModelObject();
                    user.setName(modelForm.getName());
                    user.setAge(modelForm.getAge());
                    user.setUsername(modelForm.getUsername());
                    user.setPassword(modelForm.getPassword());

                    service.update(user);

                    System.out.println("updated !");
                    feedback.success("User successfully updated");
                    target.add(feedback);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                setResponsePage(HomePage.class);
            }


            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                target.add(feedback);
            }
        });

//        form.add(new Button("back") {
//            @Override
//            public void onSubmit() {
//                super.onSubmit();
//                HomePage homePage = new HomePage();
//                setResponsePage(homePage);
//            }
//        });

        form.add(new Link<>("back") {
            @Override
            public void onClick() {
                getRequestCycle().setResponsePage(HomePage.class);
            }
        });

        add(form);
    }


    private class ModelClass implements Serializable {
        private String name;
        private String age;
        private String username;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
