package de.tum.in.goblint.oslc.servlet;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.lyo.oslc4j.client.ServiceProviderRegistryURIs;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.PrefixDefinition;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;

import de.tum.in.goblint.oslc.Constants;
import de.tum.in.goblint.oslc.resources.GoblintResource;



public class ServiceProviderFactory {
    private static Class<?>[] RESOURCE_CLASSES =
    {
        GoblintResource.class
    };

    private ServiceProviderFactory()
    {
        super();
    }

    public static ServiceProvider createServiceProvider(final String baseURI)
           throws OslcCoreApplicationException, URISyntaxException
    {
        final ServiceProvider serviceProvider = org.eclipse.lyo.oslc4j.core.model.ServiceProviderFactory.createServiceProvider(baseURI,
                                                                                                                               ServiceProviderRegistryURIs.getUIURI(),
                                                                                                                               "OSLC Goblint Service Provider",
                                                                                                                               "Reference Implementation OSLC Goblint Service Provider",
                                                                                                                               new Publisher("Goblint", "TUM"),
                                                                                                                               RESOURCE_CLASSES
        );

        final PrefixDefinition[] prefixDefinitions =
        {
            new PrefixDefinition(OslcConstants.DCTERMS_NAMESPACE_PREFIX,   new URI(OslcConstants.DCTERMS_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX, new URI(OslcConstants.OSLC_CORE_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDF_NAMESPACE_PREFIX,       new URI(OslcConstants.RDF_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDFS_NAMESPACE_PREFIX,      new URI(OslcConstants.RDFS_NAMESPACE)),
            new PrefixDefinition(Constants.GOBLINT_NAMESPACE_PREFIX,       new URI(Constants.GOBLINT_NAMESPACE)),
        };

        serviceProvider.setPrefixDefinitions(prefixDefinitions);

        return serviceProvider;
    }

}
