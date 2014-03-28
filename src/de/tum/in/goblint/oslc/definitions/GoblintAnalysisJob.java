package de.tum.in.goblint.oslc.definitions;


import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint analysis job.", describes = "goblint:GoblintAnalysisJob")
public class GoblintAnalysisJob extends TestCase {
    private URI    confFile;
    private URI    sourceFolder;
    private URI    storedInvariant;
    private String analysisStatus;

    public GoblintAnalysisJob() throws URISyntaxException {
        super();
        analysisStatus = "created";
        addType(new URI(Constants.GOBLINT_NAMESPACE + "GoblintAnalysisJob"));
    }

    @OslcPropertyDefinition("goblint:confFile")
    @OslcTitle("confFile")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("The configuration of the analysis.")
    @OslcValueType(ValueType.Resource)
    public URI getConfFile() {
        return confFile;
    }

    @OslcPropertyDefinition("goblint:sourceFolder")
    @OslcTitle("sourceFolder")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("The source files to be analyzed.")
    @OslcValueType(ValueType.Resource)
    public URI getSourceFolder() {
        return sourceFolder;
    }

    @OslcPropertyDefinition("goblint:storedInvariant")
    @OslcTitle("storedInvariant")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("An invariant asset that holds for the analysis.")
    @OslcValueType(ValueType.Resource)
    public URI getStoredInvariant() {
        return storedInvariant;
    }

    @OslcPropertyDefinition("goblint:analysisStatus")
    @OslcTitle("analysisStatus")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Status of the Analysis.")
    @OslcValueType(ValueType.String)
    public String getAnalysisStatus() {
        return analysisStatus;
    }

    public void setConfFile(URI confFile) {
        this.confFile = confFile;
    }

    public void setSourceFolder(URI sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    public void setStoredInvariant(URI storedInvariant) {
        this.storedInvariant = storedInvariant;
    }

    public void setAnalysisStatus(String analysisStatus) {
        this.analysisStatus = analysisStatus;
    }
}
