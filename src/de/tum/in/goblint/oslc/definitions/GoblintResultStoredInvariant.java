package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint analysis result: HTML", describes = "goblint:GoblintResultStoredInvariant")
public class GoblintResultStoredInvariant extends TestResult {
    private Asset goblintResultStoredInvariantAsset;

    public GoblintResultStoredInvariant() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE + "GoblintResultStoredInvariant"));
    }

    @OslcPropertyDefinition("goblint:goblintResultStoredInvariantAsset")
    @OslcTitle("goblintResultStoredInvariantAsset")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Analysis result in stored invariant form.")
    public Asset getGoblintResultStoredInvariantAsset() {
        return goblintResultStoredInvariantAsset;
    }

    public void setGoblintResultStoredInvariantAsset(Asset goblintResultStoredInvariant) {
        this.goblintResultStoredInvariantAsset = goblintResultStoredInvariant;
    }
}
