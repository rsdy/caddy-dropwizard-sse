package demo;

import static org.eclipse.jetty.servlets.CrossOriginFilter.*;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Api extends Application<ApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new Api().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-sse";
    }

    @Override
    public void run(final ApiConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().register(new SSEController());

        // Enable CORS headers; https://gist.github.com/yunspace/07d80a9ac32901f1e149
        final FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, environment.getApplicationContext().getContextPath() + "*");
        filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,POST,OPTIONS");
        filter.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
    }
}
