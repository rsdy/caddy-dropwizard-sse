package demo;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Iterator;
import javax.ws.rs.*;
import org.glassfish.jersey.media.sse.*;

@Path("events")
public final class SSEController {
    public SSEController() {}

    @GET
    @Path("/")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput streaming() {
        final EventOutput eventOutput = new EventOutput();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while(true) {

                        final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                        final String timestamp = Long.toString((new Date()).getTime());

                        eventBuilder.id(timestamp);
                        eventBuilder.name("config-change");
                        eventBuilder.data(timestamp);

                        eventOutput.write(eventBuilder.build());

                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException("Error when closing the event output.", ioClose);
                    }
                }
            }
        }).start();

        return eventOutput;
    }
}
