package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Plain folder asset", describes = "goblint:ConfFileAsset")
public class ConfFileAsset extends Asset {
    private URI confFileURI;

    public ConfFileAsset() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE+"ConfFileAsset"));
    }

    @OslcPropertyDefinition("goblint:confFileURI")
    @OslcTitle("confFileURI")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcReadOnly
    @OslcDescription("A URI to configuration file.")
    public URI getConfFileURI() {
        return confFileURI;
    }

    public void setConfFileURI(URI confFileURI) {
        this.confFileURI = confFileURI;
    }
}

