package de.tum.in.goblint.oslc.definitions;

import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

abstract public class OSLCObject extends AbstractResource {
    public int id;
    protected URI fIdentifier;
    protected String fTitle;
    protected String fDescription;
    protected List<String> fCreator;
    protected List<String> fContributor;
    protected String fCreated;
    protected String fModified;
    protected List<URI> fServiceProvider;
    protected URI fInstanceShape;

    public OSLCObject() {
        super();
        setTitle(new String("New Asset"));
        setCreator(new ArrayList<String>());
        setContributor(new ArrayList<String>());
        setCreated(new SimpleDateFormat("yyyy-mm-ddThh:mm:ss").format(Calendar.getInstance().getTime()));
    }

    @OslcPropertyDefinition("dcterms:identifier")
    @OslcTitle("identifier")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcReadOnly
    @OslcDescription("A unique identifier for a resource. Assigned by the service provider when a resource is created.")
    public URI getIdentifier() {
        return fIdentifier;
    }

    @OslcPropertyDefinition("dcterms:title")
    @OslcTitle("title")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Short name identifying a resource, often used as an abbreviated identifier for presentation to end-users. SHOULD include only content that is valid inside an XHTML element.")
    @OslcValueType(ValueType.XMLLiteral)
    public String getTitle() {
        return fTitle;
    }

    @OslcPropertyDefinition("dcterms:description")
    @OslcTitle("description")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("Descriptive text (reference: Dublin Core) about resource represented as rich text in XHTML content. SHOULD include only content that is valid and suitable inside an XHTML element.")
    public String getDescription() {
        return fDescription;
    }

    @OslcPropertyDefinition("dcterms:creator")
    @OslcTitle("creator")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Creator or creators of resource (reference: Dublin Core). It is likely that the target resource will be a  foaf:Person but that is not necessarily the case")
    public List<String> getCreator() {
        return fCreator;
    }

    @OslcPropertyDefinition("dcterms:contributor")
    @OslcTitle("contributor")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("The person(s) who are responsible for this asset. (reference: Dublin Core). It is likely that the target resource will be a foaf:Person but that is not necessarily the case.")
    public List<String> getContributor() {
        return fContributor;
    }

    @OslcPropertyDefinition("dcterms:created")
    @OslcTitle("created")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcReadOnly
    @OslcDescription("Timestamp of resource creation (reference: Dublin Core).")
    public String getCreated() {
        return fCreated;
    }

    @OslcPropertyDefinition("dcterms:modified")
    @OslcTitle("modified")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcReadOnly
    @OslcDescription("Timestamp latest resource modification (reference: Dublin Core).")
    public String getModified() {
        return fModified;
    }

    @OslcPropertyDefinition("oslc:serviceProvider")
    @OslcTitle("serviceProvider")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("The scope of a resource is a URI for the resource’s OSLC Service Provider.")
    public List<URI> getServiceProvider() {
        return fServiceProvider;
    }

    @OslcPropertyDefinition("oslc:instanceShape")
    @OslcTitle("instanceShape")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("Resource Shape that provides hints as to resource property value-types and allowed values.")
    public URI getInstanceShape() {
        return fInstanceShape;
    }

    public void setIdentifier(URI fIdentifier) {
        this.fIdentifier = fIdentifier;
    }

    public void setTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public void setDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public void setCreator(List<String> fCreator) {
        this.fCreator = fCreator;
    }

    public void setContributor(List<String> fContributor) {
        this.fContributor = fContributor;
    }

    public void setCreated(String fCreated) {
        this.fCreated = fCreated;
    }

    public void setModified(String fModified) {
        this.fModified = fModified;
    }

    public void setServiceProvider(List<URI> fServiceProvider) {
        this.fServiceProvider = fServiceProvider;
    }

    public void setInstanceShape(URI fInstanceShape) {
        this.fInstanceShape = fInstanceShape;
    }
}
