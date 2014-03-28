package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint analysis result: XML", describes = "goblint:GoblintResultXML")
public class GoblintResultXML extends TestResult {
    private URI goblintResultXML;

    public GoblintResultXML() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE + "GoblintResultXML"));
    }

    @OslcPropertyDefinition("goblint:goblintResultXML")
    @OslcTitle("goblintResultXML")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Resource)
    @OslcDescription("Analysis result in XML form.")
    public URI getGoblintResultXML() {
        return goblintResultXML;
    }

    public void setGoblintResultXML(URI goblintResultXML) {
        this.goblintResultXML = goblintResultXML;
    }
}
