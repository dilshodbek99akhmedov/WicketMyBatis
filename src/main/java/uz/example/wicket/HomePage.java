package uz.example.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.StringValue;
import uz.example.dao.User;
import uz.example.service.UserService;

import java.util.List;

/**
 * Homepage
 */
public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    UserService service = new UserService();

    /**
     * Constructor that is invoked when page is invoked without a session.*
     */
    public HomePage() {

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));

        // TODO Add your page's components here


        WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        add(container);


        LoadableDetachableModel<List<User>> listModel = new LoadableDetachableModel<>() {
            @Override
            protected List<User> load() {
                return service.getUsersList();
            }
        };


        @SuppressWarnings("unchecked")
        ListView listViewSubscriber = new ListView("listViewSubscriber", listModel) {
            protected void populateItem(ListItem item) {
                User user = (User) item.getModelObject();
                Label id = new Label("id", user.getId());
                item.add(id);
                item.add(new Label("name", user.getName()));
                item.add(new Label("age", user.getAge()));

                item.add(new Link<User>("edit") {
                    @Override
                    public void onClick() {
                        EditPage editPage = new EditPage(user);
                        setResponsePage(editPage);
                        System.out.println("edit_page");
                    }
                });

                item.add(new AjaxLink<User>("delete") {
                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        service.deleteById(user.getId());

                        System.out.println("deleted: " + ((User) item.getModelObject()).getId());

                        ajaxRequestTarget.add(container);
                    }
                });

            }
        };
        container.add(listViewSubscriber);

        // Link added

        add(new Link<>("create") {
            @Override
            public void onClick() {
                setResponsePage(CreatePage.class);
            }
        });
        add(new FeedbackPanel("feedbackMessage",
                new ExactErrorLevelFilter(FeedbackMessage.ERROR)));
        add(new FeedbackPanel("succesMessage",
                new ExactErrorLevelFilter(FeedbackMessage.SUCCESS)));


    }

    static class ExactErrorLevelFilter implements IFeedbackMessageFilter {
        private final int errorLevel;

        public ExactErrorLevelFilter(int errorLevel) {
            this.errorLevel = errorLevel;
        }

        public boolean accept(FeedbackMessage message) {
            return message.getLevel() == errorLevel;
        }

    }

    public Session newSession(Request req, Response res) {
        StringValue session = req.getPostParameters().getParameterValue("session");
        Session seon = getSession();

        return null;
    }
}
