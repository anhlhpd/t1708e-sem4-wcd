package pubsub.demo;

import com.google.api.ProjectProperties;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.appengine.repackaged.com.google.api.client.auth.oauth2.Credential;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import pubsub.demo.util.PubsubConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PublisherController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        ProjectTopicName topicName =
                ProjectTopicName.of(PubsubConstant.GOOGLE_CLOUD_PROJECT_ID, PubsubConstant.TOPIC_ID);

        Publisher publisher =
                Publisher.newBuilder(topicName)
                        .setCredentialsProvider(new CredentialsProvider() {
                            @Override
                            public Credentials getCredentials() throws IOException {
                                return GoogleCredentials.fromStream(
                                        getClass()
                                                .getClassLoader()
                                                .getResourceAsStream(PubsubConstant.KEY_PATH));
                            }
                        }).build();
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
        publisher.publish(pubsubMessage);
        resp.getWriter().print("Send message success!");
    }
}
