package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Plain folder asset", describes = "goblint:StoredInvariantAsset")
public class StoredInvariantAsset extends Asset {
    private URI storedInvariantFileURL;

    public StoredInvariantAsset() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE+"StoredInvariantAsset"));
    }
    @OslcPropertyDefinition("goblint:storedInvariantURI")
    @OslcTitle("storedInvariantURI")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcReadOnly
    @OslcDescription("A URI to a the stored invariant file.")
    public URI getStoredInvariantFileURL() {
        return storedInvariantFileURL;
    }

    public void setStoredInvariantFileURL(URI storedInvariant) {
        this.storedInvariantFileURL = storedInvariant;
    }
}
