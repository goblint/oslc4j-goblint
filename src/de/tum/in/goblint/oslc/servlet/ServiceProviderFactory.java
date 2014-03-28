package de.tum.in.goblint.oslc.servlet;

import de.tum.in.goblint.oslc.Constants;
import de.tum.in.goblint.oslc.resources.GoblintResource;
import org.eclipse.lyo.oslc4j.client.ServiceProviderRegistryURIs;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.PrefixDefinition;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;

import java.net.URI;
import java.net.URISyntaxException;


public class ServiceProviderFactory {
    private static Class<?>[] RESOURCE_CLASSES =
            {
                    GoblintResource.class
            };

    private ServiceProviderFactory() {
        super();
    }

    public static ServiceProvider createServiceProvider(final String baseURI)
            throws OslcCoreApplicationException, URISyntaxException {
        final ServiceProvider serviceProvider = org.eclipse.lyo.oslc4j.core.model.ServiceProviderFactory.createServiceProvider(baseURI,
                ServiceProviderRegistryURIs.getUIURI(),
                "OSLC Goblint Service Provider",
                "Reference Implementation OSLC Goblint Service Provider",
                new Publisher("Goblint", "TUM"),
                RESOURCE_CLASSES
        );

        final PrefixDefinition[] prefixDefinitions =
                {
                        new PrefixDefinition("dcterms", new URI("http://purl.org/dc/terms/")),
                        new PrefixDefinition("rdf", new URI("http://www.w3.org/1999/02/22-rdf-syntax-ns#")),
                        new PrefixDefinition("rdfs", new URI("http://www.w3.org/2000/01/rdf-schema#")),
                        new PrefixDefinition("oslc", new URI("http://open-services.net/ns/core#")),
                        new PrefixDefinition("oslcqm", new URI("http://open-services.net/ns/qm#")),
                        new PrefixDefinition("oslcasset", new URI("http://open-services.net/ns/asset#")),
                        new PrefixDefinition("goblint", new URI(Constants.GOBLINT_NAMESPACE)),

                };

        serviceProvider.setPrefixDefinitions(prefixDefinitions);

        return serviceProvider;
    }

}
