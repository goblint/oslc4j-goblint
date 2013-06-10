package de.tum.in.goblint.oslc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.datatype.DatatypeConfigurationException;

import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.provider.jena.JenaModelHelper;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileUtils;

public class Persistence {
	
	private final static Map<String, GoblintInput>   INPUT_URL_MAP = new TreeMap<String, GoblintInput>();
	private final static Map<Integer, GoblintInput>  INPUT_MAP     = new HashMap<Integer, GoblintInput>();
	private final static Map<Integer, GoblintOutput> OUTPUT_MAP    = new HashMap<Integer, GoblintOutput>();
	 
	
	private Persistence(){
		super();
	}
	

    public static boolean load(final String uriString)
           throws DatatypeConfigurationException,
                  FileNotFoundException,
                  IllegalAccessException,
                  IllegalArgumentException,
                  InstantiationException,
                  InvocationTargetException,
                  OslcCoreApplicationException,
                  URISyntaxException,
                  SecurityException,
                  NoSuchMethodException
    {

        final File file = new File(Paths.getPersistencePath() + File.separatorChar + "goblintOslcData.xml");

        if ((file.exists()) &&
            (file.isFile()) &&
            (file.canRead()))
        {
            final Model model = ModelFactory.createDefaultModel();

            model.read(new FileInputStream(file),
                       null,
                       FileUtils.langXMLAbbrev);

            final Object[] resources = JenaModelHelper.fromJenaModel(model,
                                                                     GoblintInput.class);

            synchronized(INPUT_MAP)
            {
            	INPUT_MAP.clear();

                if (resources != null)
                {
                    for (final Object resource : resources)
                    {
                        if (resource instanceof GoblintInput)
                        {
                            final GoblintInput input = (GoblintInput) resource;

                            final int identifier = input.getId();

                            INPUT_MAP.put(identifier, input);
                        }
                    }
                }
            }

            return true;
        }

        return false;
    }

    public static void save(final String uriString)
           throws URISyntaxException,
                  OslcCoreApplicationException,
                  IllegalArgumentException,
                  DatatypeConfigurationException,
                  IllegalAccessException,
                  InvocationTargetException,
                  FileNotFoundException
    {
    	final String fileName = Paths.getPersistencePath() + File.separatorChar + "goblintOslcData.xml";
    			
        System.out.printf("Saving data to %s.\n",fileName);

        final GoblintInput[] inputs = getInputs();

        final Model model = JenaModelHelper.createJenaModel(inputs);

        if (model != null)
        {
            final RDFWriter writer = model.getWriter(FileUtils.langXMLAbbrev);

            writer.setProperty("showXmlDeclaration", "true");

            writer.write(model, new FileOutputStream(fileName), null);
        }
    }

    
    public static GoblintInput[] getInputs()
    {
        synchronized (INPUT_MAP)
        {
            return INPUT_MAP.values().toArray(new GoblintInput[INPUT_MAP.size()]);
        }
    }

    public static GoblintInput getInput(final int identifier)
    {
        synchronized (INPUT_MAP)
        {
            return INPUT_MAP.get(identifier);
        }
    }

    public static GoblintInput getInput(final String identifier)
    {
        synchronized (INPUT_URL_MAP)
        {
            return INPUT_URL_MAP.get(identifier);
        }
    }

    public static void addInput(final GoblintInput input)
    {
    	synchronized (INPUT_URL_MAP) {
    		System.out.printf("url:%s\n",input.getAbout().toString());
        	INPUT_URL_MAP.put(input.getAbout().toString(), input);			
		}
        synchronized (INPUT_MAP)
        {
        	INPUT_MAP.put(input.getId(), input);
        }
    }

    public static GoblintInput deleteInput(final int identifier)
    {
    	synchronized (INPUT_MAP)
        {
            return INPUT_MAP.remove(identifier);
        }
    }


	public static void addOutput(GoblintOutput output) {
        synchronized (OUTPUT_MAP)
        {
        	OUTPUT_MAP.put(output.getId(), output);
        }		
	}
	
	
    public static GoblintOutput[] getOutputs()
    {
        synchronized (OUTPUT_MAP)
        {
            return OUTPUT_MAP.values().toArray(new GoblintOutput[OUTPUT_MAP.size()]);
        }
    }

    public static GoblintOutput getOutput(final int identifier)
    {
        synchronized (OUTPUT_MAP)
        {
            return OUTPUT_MAP.get(identifier);
        }
    }

    public static GoblintOutput deleteOutput(final int identifier)
    {
        synchronized (OUTPUT_MAP)
        {
            return OUTPUT_MAP.remove(identifier);
        }
    }

}
