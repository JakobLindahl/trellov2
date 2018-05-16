package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import javax.ws.rs.sse.OutboundSseEvent;

@Path("broadcast")
public class BroadcasterResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void getServerSentEvents(@Context SseEventSink eventSink, @Context Sse sse) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    final OutboundSseEvent event = sse.newEventBuilder()
                            .name("message-to-client")
                            .data(String.class, "Hello world " + i + "!")
                            .build();
                    eventSink.send(event);
                }
            }
        }).start();
    }

}