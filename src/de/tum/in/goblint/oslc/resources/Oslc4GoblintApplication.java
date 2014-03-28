package de.tum.in.goblint.oslc.resources;

import de.tum.in.goblint.oslc.Constants;
import de.tum.in.goblint.oslc.definitions.*;
import org.eclipse.lyo.oslc4j.application.OslcWinkApplication;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.*;
import org.eclipse.lyo.oslc4j.core.model.Error;
import org.eclipse.lyo.oslc4j.core.resources.ServiceProviderCatalogResource;
import org.eclipse.lyo.oslc4j.core.resources.ServiceProviderResource;
import org.eclipse.lyo.oslc4j.provider.jena.JenaProvidersRegistry;
import org.eclipse.lyo.oslc4j.provider.json4j.Json4JProvidersRegistry;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Oslc4GoblintApplication 
	extends OslcWinkApplication
{

	private static final Set<Class<?>>         RESOURCE_CLASSES                          = new HashSet<>();
	private static final Map<String, Class<?>> RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP = new HashMap<>();

	static
	{
		RESOURCE_CLASSES.addAll(JenaProvidersRegistry.getProviders());
		RESOURCE_CLASSES.addAll(Json4JProvidersRegistry.getProviders());
		RESOURCE_CLASSES.add(GoblintResource.class);
        RESOURCE_CLASSES.add(ServiceProviderCatalogResource.class);
        RESOURCE_CLASSES.add(ServiceProviderResource.class);

		RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Constants.PATH_GOBLINT, GoblintResource.class);

        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("ConfFileAsset"               , ConfFileAsset.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("GoblintAnalysisJob"          , GoblintAnalysisJob.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("GoblintResultHTML"           , GoblintResultHTML.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("GoblintResultStoredInvariant", GoblintResultStoredInvariant.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("GoblintResultWarning"        , GoblintResultWarning.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("GoblintResultXML"            , GoblintResultXML.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("SourceFolderAsset"           , SourceFolderAsset.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("StoredInvariantAsset"        , StoredInvariantAsset.class);

        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("Asset"     , Asset.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("TestCase"  , TestCase.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put("TestResult", TestResult.class);

        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_ALLOWED_VALUES,           AllowedValues.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_COMPACT,                  Compact.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_CREATION_FACTORY,         CreationFactory.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_DIALOG,                   Dialog.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_ERROR,                    Error.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_EXTENDED_ERROR,           ExtendedError.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_OAUTH_CONFIGURATION,      OAuthConfiguration.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PREFIX_DEFINITION,        PrefixDefinition.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PREVIEW,                  Preview.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PROPERTY,                 Property.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_PUBLISHER,                Publisher.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_QUERY_CAPABILITY,         QueryCapability.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_RESOURCE_SHAPE,           ResourceShape.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_SERVICE,                  Service.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_SERVICE_PROVIDER,         ServiceProvider.class);
        RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(OslcConstants.PATH_SERVICE_PROVIDER_CATALOG, ServiceProviderCatalog.class);	}

	public Oslc4GoblintApplication()
			throws OslcCoreApplicationException,
			URISyntaxException, IOException
	{
		super(RESOURCE_CLASSES,
				OslcConstants.PATH_RESOURCE_SHAPES,
				RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP);
		
	}
}

