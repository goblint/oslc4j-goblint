package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Plain folder asset", describes = "goblint:SourceFolderAsset")
public class SourceFolderAsset extends Asset {
    private URI sourceFolderURI;

    public SourceFolderAsset() throws URISyntaxException {
        super();
        addType(new URI(Constants.GOBLINT_NAMESPACE+"SourceFolderAsset"));
    }

    @OslcPropertyDefinition("goblint:sourceFolderURI")
    @OslcTitle("sourceFolderURI")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcReadOnly
    @OslcDescription("A URI to a listing of files and folders.")
    public URI getSourceFolderURI() {
        return sourceFolderURI;
    }

    public void setSourceFolderURI(URI fBaseURI) {
        this.sourceFolderURI = fBaseURI;
    }
}
