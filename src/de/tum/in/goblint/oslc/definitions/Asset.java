package de.tum.in.goblint.oslc.definitions;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.XMLLiteral;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@OslcNamespace("http://open-services.net/ns/asset#")
@OslcResourceShape(title = "OSLC Asset Management Resource", describes = "http://open-services.net/ns/asset#Asset")
public class Asset extends OSLCObject {
    private String fGuid;
    private String fVersion;
    private Object fRelationshipType;
    private List<Object> fRelation;
    private URI fArtifactFactory;
    private List<Object> fArtifact;
    private List<String> fTag;
    private String fSerialNumber;
    private String fModel;
    private String fManufacturer;
    private XMLLiteral fAbstract;
    private List<Object> fCategorization;
    private Object fState;
    private URI fType;

    public Asset() throws URISyntaxException {
        super();
        setRelation(new ArrayList<>());
        setArtifact(new ArrayList<>());
        setTag(new ArrayList<String>());
        setCategorization(new ArrayList<>());
        addType(new URI(Constants.GOBLINT_NAMESPACE + "Asset"));
    }

    @OslcPropertyDefinition("oslc_asset:guid")
    @OslcTitle("guid")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription(" An identifier for the asset. Assigned by the service provider when a resource is created. Different versions of the same asset will share the same identifier.")
    public String getGuid() {
        return fGuid;
    }

    @OslcPropertyDefinition("oslc_asset:version")
    @OslcTitle("version")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("The version of the asset. Possible values may include ‘1.0’, ‘2.0’, etc.")
    public String getVersion() {
        return fVersion;
    }

    @OslcPropertyDefinition("dcterms:abstract")
    @OslcTitle("abstract")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("Short description (reference: Dublin Core) or often a single line summary of the resource")
    public XMLLiteral getAbstract() {
        return fAbstract;
    }

    @OslcPropertyDefinition("dcterms:type")
    @OslcTitle("type")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("The type of the asset based on values defined by the service provider. This specification does not define the resource for this property, however it should contain a dcterms:title property.")
    public URI getType() {
        return fType;
    }

    @OslcPropertyDefinition("oslc_asset:state")
    @OslcTitle("state")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("Used to indicate the state of the asset based on values defined by the service provider. This specification does not define the resource for this property, however it should contain a dcterms:title property.")
    public Object getState() {
        return fState;
    }

    @OslcPropertyDefinition("oslc_asset:categorization")
    @OslcTitle("categorization")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("A categorization to classify an asset. The category schema values are defined by the service provider. This specification does not define the resource for this property, however it should contain a dcterms:title property.")
    public List<Object> getCategorization() {
        return fCategorization;
    }

    @OslcPropertyDefinition("oslc_asset:manufacturer")
    @OslcTitle("manufacturer")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("The name of the asset manufacturer.")
    public String getManufacturer() {
        return fManufacturer;
    }

    @OslcPropertyDefinition("oslc_asset:model")
    @OslcTitle("model")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("The value of the asset model.")
    public String getModel() {
        return fModel;
    }

    @OslcPropertyDefinition("oslc_asset:serialNumber")
    @OslcTitle("serialNumber")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("The serial number assigned by the asset manufacturer.")
    public String getSerialNumber() {
        return fSerialNumber;
    }

    @OslcPropertyDefinition("oslc_asset:tag")
    @OslcTitle("tag")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Specifies the asset tag value for an Asset. Asset tags are typically human readable labels. For hardware assets, these tags are durable, securely attached to equipment, and may also be readable by barcode and/or RFID.")
    public List<String> getTag() {
        return fTag;
    }

    @OslcPropertyDefinition("oslc_asset:artifact")
    @OslcTitle("artifact")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("An Artifact fragment contained in this Asset resource.")
    public List<Object> getArtifact() {
        return fArtifact;
    }

    @OslcPropertyDefinition("oslc_asset:artifactFactory")
    @OslcTitle("artifactFactory")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Resource URI used to post new artifacts to the asset.")
    public URI getArtifactFactory() {
        return fArtifactFactory;
    }

    @OslcPropertyDefinition("dcterms:relation")
    @OslcTitle("relation")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("This relationship is loosely coupled and has no specific meaning. Details about this relationship may be included in a reified statement.")
    public List<Object> getRelation() {
        return fRelation;
    }

    @OslcPropertyDefinition("oslc_asset:relationshipType")
    @OslcTitle("relationshipType")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("The type of this relationship from the perspective of the dcterms:relation resource based on values defined by the service provider. This specification does not define the resource for this property, however it should contain a dcterms:title property.")
    public Object getRelationshipType() {
        return fRelationshipType;
    }

    public void setGuid(String fGuid) {
        this.fGuid = fGuid;
    }

    public void setVersion(String fVersion) {
        this.fVersion = fVersion;
    }

    public void setRelationshipType(Object fRelationshipType) {
        this.fRelationshipType = fRelationshipType;
    }

    public void setRelation(List<Object> fRelation) {
        this.fRelation = fRelation;
    }

    public void setArtifactFactory(URI fArtifactFactory) {
        this.fArtifactFactory = fArtifactFactory;
    }

    public void setArtifact(List<Object> fArtifact) {
        this.fArtifact = fArtifact;
    }

    public void setTag(List<String> fTag) {
        this.fTag = fTag;
    }

    public void setSerialNumber(String fSerialNumber) {
        this.fSerialNumber = fSerialNumber;
    }

    public void setModel(String fModel) {
        this.fModel = fModel;
    }

    public void setManufacturer(String fManufacturer) {
        this.fManufacturer = fManufacturer;
    }

    public void setAbstract(XMLLiteral fAbstract) {
        this.fAbstract = fAbstract;
    }

    public void setCategorization(List<Object> fCategorization) {
        this.fCategorization = fCategorization;
    }

    public void setState(Object fState) {
        this.fState = fState;
    }

    public void setType(URI fType) {
        this.fType = fType;
    }
}