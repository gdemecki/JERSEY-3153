package com.xyz;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Main entry point of the application.
 */
public class AppRunner {

    public static void main(String[] args) throws Exception {
        startJettyAndJersey();
    }

    static void startJettyAndJersey()
            throws Exception {

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase("src/main/webapp");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        // configure Jersey
        ResourceConfig config = new ResourceConfig();
        config.packages("com.xyz.rest.resources");
        config.packages("com.xyz.rest.errors");
        config.register(JacksonFeature.class);

        // assign Jersey servlet
        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        context.addServlet(jerseyServlet, "/api/*");

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }

}

