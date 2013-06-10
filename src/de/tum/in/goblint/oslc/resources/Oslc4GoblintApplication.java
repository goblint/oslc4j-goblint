package de.tum.in.goblint.oslc.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.lyo.oslc4j.application.OslcWinkApplication;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.provider.jena.JenaProvidersRegistry;
import org.eclipse.lyo.oslc4j.provider.json4j.Json4JProvidersRegistry;

import de.tum.in.goblint.oslc.Constants;
import de.tum.in.goblint.oslc.GoblintInput;


public class Oslc4GoblintApplication 
	extends OslcWinkApplication
{

	private static final Set<Class<?>>         RESOURCE_CLASSES                          = new HashSet<Class<?>>();
	private static final Map<String, Class<?>> RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP = new HashMap<String, Class<?>>();

	static
	{
		RESOURCE_CLASSES.addAll(JenaProvidersRegistry.getProviders());
		RESOURCE_CLASSES.addAll(Json4JProvidersRegistry.getProviders());
		RESOURCE_CLASSES.add(GoblintResource.class);

		RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP.put(Constants.PATH_GOBLINT, GoblintInput.class);
	}

	public Oslc4GoblintApplication()
			throws OslcCoreApplicationException,
			URISyntaxException, IOException
	{
		super(RESOURCE_CLASSES,
				OslcConstants.PATH_RESOURCE_SHAPES,
				RESOURCE_SHAPE_PATH_TO_RESOURCE_CLASS_MAP);
		
	}
}
