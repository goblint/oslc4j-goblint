package de.tum.in.goblint.oslc;

import de.tum.in.goblint.oslc.definitions.*;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Persistence {
	private static Map<Integer, ConfFileAsset>                confFileAssets                = new HashMap<>();
    private static Map<Integer, SourceFolderAsset>            sourceFolderAssets            = new HashMap<>();
    private static Map<Integer, StoredInvariantAsset>         storedInvariantAssets         = new HashMap<>();
    private static Map<Integer, GoblintResultWarning>         goblintResultWarnings         = new HashMap<>();
    private static Map<Integer, GoblintResultXML>             goblintResultXML              = new HashMap<>();
    private static Map<Integer, GoblintResultHTML>            goblintResultHTML             = new HashMap<>();
    private static Map<Integer, GoblintResultStoredInvariant> goblintResultStoredInvariants = new HashMap<>();
    private static Map<Integer, GoblintAnalysisJob>           goblintAnalysisJobs           = new HashMap<>();


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
//            final Model model = ModelFactory.createDefaultModel();
//
//            model.read(new FileInputStream(file),
//                       null,
//                       FileUtils.langXMLAbbrev);
//
//            final Collection<Object> resources = JenaModelHelper.fromJenaModel(model,
//                                                                     GoblintInput.class);
//
//            synchronized(INPUT_MAP)
//            {
//            	INPUT_MAP.clear();
//
//                if (resources != null)
//                {
//                    for (final Object resource : resources)
//                    {
//                        if (resource instanceof GoblintInput)
//                        {
//                            final GoblintInput input = (GoblintInput) resource;
//
//                            final int identifier = input.getId();
//
//                            INPUT_MAP.put(identifier, input);
//                        }
//                    }
//                }
//            }

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

//        final Collection<GoblintInput> inputs = getInputs();
//
//        final Model model = JenaModelHelper.createJenaModel(inputs);
//
//        if (model != null)
//        {
//            final RDFWriter writer = model.getWriter(FileUtils.langXMLAbbrev);
//
//            writer.setProperty("showXmlDeclaration", "true");
//
//            writer.write(model, new FileOutputStream(fileName), null);
//        }
    }


    public static void addConfFile(ConfFileAsset input) {
        confFileAssets.put(input.id, input);
    }

    public static Collection<ConfFileAsset> getConfFileAssets() {
        return confFileAssets.values();
    }

    public static ConfFileAsset getConfFileAssets(int inputId) {
        return confFileAssets.get(inputId);
    }

    public static void addSourceFolder(SourceFolderAsset input) {
        sourceFolderAssets.put(input.id, input);
    }

    public static Collection<SourceFolderAsset> getSourceFolderAssets() {
        return sourceFolderAssets.values();
    }

    public static ConfFileAsset getSourceFolderAssets(int inputId) {
        return confFileAssets.get(inputId);
    }

    public static void addStoredInvariant(StoredInvariantAsset input) {
        storedInvariantAssets.put(input.id, input);
    }

    public static Collection<StoredInvariantAsset> getStoredInvariantAssets() {
        return storedInvariantAssets.values();
    }

    public static StoredInvariantAsset getStoredInvariantAssets(int inputId) {
        return storedInvariantAssets.get(inputId);
    }

    public static void addGoblintResultWarning(GoblintResultWarning input) {
        goblintResultWarnings.put(input.id, input);
    }

    public static Collection<GoblintResultWarning> getGoblintResultWarnings() {
        return goblintResultWarnings.values();
    }

    public static GoblintResultWarning getGoblintResultWarnings(int inputId) {
        return goblintResultWarnings.get(inputId);
    }

    public static void addGoblintResultXML(GoblintResultXML input) {
        goblintResultXML.put(input.id, input);
    }

    public static Collection<GoblintResultXML> getGoblintResultXML() {
        return goblintResultXML.values();
    }

    public static GoblintResultXML getGoblintResultXML(int inputId) {
        return goblintResultXML.get(inputId);
    }

    public static void addGoblintResultHTML(GoblintResultHTML input) {
        goblintResultHTML.put(input.id, input);
    }

    public static Collection<GoblintResultHTML> getGoblintResultHTML() {
        return goblintResultHTML.values();
    }

    public static GoblintResultHTML getGoblintResultHTML(int inputId) {
        return goblintResultHTML.get(inputId);
    }

    public static void addGoblintResultStoredInvariants(GoblintResultStoredInvariant input) {
        goblintResultStoredInvariants.put(input.id, input);
    }

    public static Collection<GoblintResultStoredInvariant> getGoblintResultStoredInvariants() {
        return goblintResultStoredInvariants.values();
    }

    public static GoblintResultStoredInvariant getGoblintResultStoredInvariants(int inputId) {
        return goblintResultStoredInvariants.get(inputId);
    }

    public static Collection<GoblintAnalysisJob> getGoblintAnalysisJobs() {
        return goblintAnalysisJobs.values();
    }

    public static GoblintAnalysisJob getGoblintAnalysisJobs(int inputId) {
        return goblintAnalysisJobs.get(inputId);
    }

    public static void addGoblintAnalysisJobs(GoblintAnalysisJob input) {
        goblintAnalysisJobs.put(input.id, input);
    }
}
