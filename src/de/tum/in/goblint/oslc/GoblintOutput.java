package de.tum.in.goblint.oslc;

import java.net.URI;

import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

@OslcNamespace(Constants.GOBLINT_NAMESPACE)
@OslcResourceShape(title = "Goblint Output", describes = Constants.TYPE_GOBLINT_OUTPUT)
public class GoblintOutput 
	extends AbstractResource
{
	private URI 			goblintInputURI;
	private GoblintInput	goblintInput;
	
    @OslcDescription("URI of the Goblint input.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "goblintInput")
    @OslcReadOnly
    @OslcTitle("input")
    public URI getGoblintInputURI() {
		return goblintInputURI;
	}
    
	public void setGoblintInputURI(URI goblintInputURI) {
		this.goblintInputURI = goblintInputURI;
	}
	
}
