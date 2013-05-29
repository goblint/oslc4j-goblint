package de.tum.in.goblint.oslc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.TreeMap;

import javax.xml.datatype.DatatypeConfigurationException;

import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.provider.jena.JenaModelHelper;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileUtils;

public class Persistence {
	
	private final static TreeMap<Integer, GoblintInput> INPUT_MAP = new TreeMap<Integer, GoblintInput>();
	
	/*private final static TreeMap<Integer, GoblintOutput> OUTPUT_MAP = new TreeMap<Integer, GoblintOutput>();
	 */
	
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
        final String fileName = createFileName(uriString);

        final File file = new File(fileName);

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
        final String fileName = createFileName(uriString);

        final GoblintInput[] inputs = getInputs();

        final Model model = JenaModelHelper.createJenaModel(inputs);

        if (model != null)
        {
            final RDFWriter writer = model.getWriter(FileUtils.langXMLAbbrev);

            writer.setProperty("showXmlDeclaration", "true");

            writer.write(model, new FileOutputStream(fileName), null);
        }
    }

    private static String createFileName(final String uriString)
            throws URISyntaxException
    {
        final URI uri = new URI(uriString);

        final String host = uri.getHost();
        final int    port = uri.getPort();
        final String path = uri.getPath();

        final String tmpDir = System.getProperty("java.io.tmpdir");

        final String fileName = tmpDir + "/" + host + "_" + port + path.replace('/', '_').replace('\\', '_') + ".xml";

        return fileName;
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

    public static void addInput(final GoblintInput input)
    {
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


 
}
