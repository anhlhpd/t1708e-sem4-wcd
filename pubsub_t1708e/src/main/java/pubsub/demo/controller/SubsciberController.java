package pubsub.demo.controller;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import pubsub.demo.util.PubsubConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubsciberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of(PubsubConstant.GOOGLE_CLOUD_PROJECT_ID, PubsubConstant.SUBSCRIPTION_ID);
        Subscriber subscriber = Subscriber.newBuilder(subscriptionName, new MessageReceiver() {
            @Override
            public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
                System.out.println("Id : " + pubsubMessage.getMessageId());
                System.out.println("Data : " + pubsubMessage.getData().toStringUtf8());
                ackReplyConsumer.ack();
            }
        })
        .setCredentialsProvider(new CredentialsProvider() {
            @Override
            public Credentials getCredentials() throws IOException {
                return GoogleCredentials.fromStream(
                        getClass().getClassLoader().getResourceAsStream("key.json")
                );
            }
        }).build();
        subscriber.startAsync();
    }
}
