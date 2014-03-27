package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint analysis result: HTML", describes = "goblint:GoblintResultHTML")
public class GoblintResultHTML extends TestResult {
    private URI goblintResultHTML;

    public GoblintResultHTML() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE + "GoblintResultHTML"));
    }

    @OslcPropertyDefinition("goblint:goblintResultHTML")
    @OslcTitle("goblintResultHTML")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Analysis result in HTML form.")
    public URI getGoblintResultHTML() {
        return goblintResultHTML;
    }

    public void setGoblintResultHTML(URI goblintResultHTML) {
        this.goblintResultHTML = goblintResultHTML;
    }
}
