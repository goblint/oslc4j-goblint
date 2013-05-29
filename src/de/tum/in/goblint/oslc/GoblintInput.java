package de.tum.in.goblint.oslc;

import java.net.URI;

import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint Input", describes = Constants.TYPE_GOBLINT_INPUT)
public final class GoblintInput 
	extends AbstractResource
{
	private int    id;
    private URI    fileBaseURI;
	private URI    confFile;
	private String callString;
	private URI    serviceProvider;

	public GoblintInput()
    {
        super();
    }

    @OslcDescription("URI of the file system root.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "fileBaseURI")
    @OslcReadOnly
    @OslcTitle("files")
    public URI getFileBaseURI() {
		return fileBaseURI;
	}

	public void setFileBaseURI(final URI fileBaseURI) {
		this.fileBaseURI = fileBaseURI;
	}

    @OslcDescription("Configuration File of Goblint.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "confFile")
    @OslcReadOnly
    @OslcTitle("conf")
	public URI getConfFile() {
		return confFile;
	}

	public void setConfFile(URI confFile) {
		this.confFile = confFile;
	}

    @OslcDescription("Goblint Call String")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "callString")
    @OslcReadOnly
    @OslcTitle("callString")
	public String getCallString() {
		return callString;
	}

	public void setCallString(String callString) {
		this.callString = callString;
	}

    @OslcDescription("Identificator for an input.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "id")
    @OslcReadOnly
    @OslcTitle("id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OslcDescription("The scope of a resource is a URI for the resource's OSLC Service Provider.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "serviceProvider")
    @OslcRange(OslcConstants.TYPE_SERVICE_PROVIDER)
    @OslcReadOnly
    @OslcTitle("Service Provider")
    public URI getServiceProvider()
    {
        return serviceProvider;
    }
	
	public void setServiceProvider(final URI sp){
		serviceProvider = sp;
	}
}
