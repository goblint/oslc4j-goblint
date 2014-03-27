package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint analysis result: HTML", describes = "goblint:GoblintResultWarning")
public class GoblintResultWarning extends TestResult {
    private int goblintResultWarningLine;
    private String goblintResultWarningFile;
    private String goblintResultWarningDescription;

    public GoblintResultWarning() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE + "GoblintResultWarning"));
    }

    @OslcPropertyDefinition("goblint:goblintResultWarningLine")
    @OslcTitle("goblintResultWarningLine")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("The line with which the warning is associated with.")
    public int getGoblintResultWarningLine() {
        return goblintResultWarningLine;
    }

    @OslcPropertyDefinition("goblint:goblintResultWarningFile")
    @OslcTitle("goblintResultWarningFile")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("The file with which the warning is associated with.")
    public String getGoblintResultWarningFile() {
        return goblintResultWarningFile;
    }

    @OslcPropertyDefinition("goblint:goblintResultWarningDescription")
    @OslcTitle("goblintResultWarningDescription")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Textual description of the warning.")
    public String getGoblintResultWarningDescription() {
        return goblintResultWarningDescription;
    }

    public void setGoblintResultWarningLine(int goblintResultWarningLine) {
        this.goblintResultWarningLine = goblintResultWarningLine;
    }

    public void setGoblintResultWarningFile(String goblintResultWarningFile) {
        this.goblintResultWarningFile = goblintResultWarningFile;
    }

    public void setGoblintResultWarningDescription(String goblintResultWarningDescription) {
        this.goblintResultWarningDescription = goblintResultWarningDescription;
    }
}
