package de.tum.in.goblint.oslc.servlet;

import de.tum.in.goblint.oslc.Persistence;
import org.eclipse.lyo.oslc4j.client.ServiceProviderRegistryClient;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.servlet.ServiceProviderCatalogSingleton;
import org.eclipse.lyo.oslc4j.provider.jena.JenaProvidersRegistry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServletListener
        implements ServletContextListener {
    private static final String PROPERTY_SCHEME = ServletListener.class.getPackage().getName() + ".scheme";
    private static final String PROPERTY_PORT = ServletListener.class.getPackage().getName() + ".port";
    private static final int REGISTRATION_DELAY = 5000; //Delay before contacting OSLC4JRegistry
    private static final Logger logger = Logger.getLogger(ServletListener.class.getName());

    private static final String HOST = "localhost";

    private ServiceProviderRegistryClient client;
    private String serviceProviderIdentifier;

    public ServletListener() {
        super();
    }

    //@Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        final String basePath = generateBasePath(servletContextEvent);

        System.out.println("Terminating ...");

        if (serviceProviderIdentifier != null) {
            try {
                ServiceProviderCatalogSingleton.deregisterServiceProvider(serviceProviderIdentifier);
            } catch (final Exception exception) {
                logger.log(Level.SEVERE, "Unable to deregister with service provider catalog", exception);
            } finally {
                serviceProviderIdentifier = null;
            }
        }

        if (client != null) {
            //Don't try to deregister if catalog is on same HOST as us.   
            //TODO:  Use a regex that accounts for port as well.
            if (!client.getClient().getUri().contains(HOST)) {
                try {
                    client.deregisterServiceProvider(ServiceProviderSingleton.getServiceProviderURI());
                } catch (final Exception exception) {
                    logger.log(Level.SEVERE, "Unable to deregister with service provider catalog", exception);
                } finally {
                    client = null;
                    ServiceProviderSingleton.setServiceProviderURI(null);
                }
            }
        }

        try {
            Persistence.save(basePath);
        } catch (final Exception exception) {
            logger.log(Level.SEVERE, "Unable to save", exception);
        }
    }

    //    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        final String basePath = generateBasePath(servletContextEvent);
        ServiceProvider serviceProvider = null;
        try {
            serviceProvider = org.eclipse.lyo.oslc4j.core.servlet.ServiceProviderFactory.createServiceProvider(basePath);
            final ServiceProvider registeredServiceProvider = org.eclipse.lyo.oslc4j.core.servlet.ServiceProviderCatalogSingleton.registerServiceProvider(basePath, serviceProvider);

            serviceProviderIdentifier = registeredServiceProvider.getIdentifier();
        } catch (OslcCoreApplicationException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer();
        timer.schedule(new RegistrationTask(basePath), REGISTRATION_DELAY);

    }

    private static String generateBasePath(final ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();

        String scheme = System.getProperty(PROPERTY_SCHEME);
        if (scheme == null) {
            scheme = servletContext.getInitParameter(PROPERTY_SCHEME);
        }

        String port = System.getProperty(PROPERTY_PORT);
        if (port == null) {
            port = servletContext.getInitParameter(PROPERTY_PORT);
        }

        return scheme + "://" + HOST + ":" + port + servletContext.getContextPath();
    }

    private static String getHost() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (final UnknownHostException exception) {
            return "localhost";
        }
    }

    private class RegistrationTask extends TimerTask {
        String basePath;

        protected RegistrationTask(String basePath) {
            this.basePath = basePath;
        }

        @Override
        public void run() {
            final URI serviceProviderURI;
            try {
                final ServiceProvider serviceProvider = ServiceProviderFactory.createServiceProvider(basePath);

                client = new ServiceProviderRegistryClient(JenaProvidersRegistry.getProviders());

                serviceProviderURI = client.registerServiceProvider(serviceProvider);

                ServiceProviderSingleton.setServiceProviderURI(serviceProviderURI);

                logger.log(Level.INFO, "Service provider registration complete.");
            } catch (final Exception exception) {
                client = null;

                logger.log(Level.SEVERE, "Unable to register with service provider catalog", exception);

                return;
            }

            try {
                final Populate populate = new Populate(basePath, serviceProviderURI);

                if (Persistence.load(basePath)) {
                    // References to ServiceProvider have to be updated
                    populate.fixup();
                } else {
                    populate.populate();
                }
            } catch (final Exception exception) {
                logger.log(Level.SEVERE, "Unable to load", exception);
            }

        }

    }

}
