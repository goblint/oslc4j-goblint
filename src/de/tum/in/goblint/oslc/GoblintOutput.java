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
	private int 		    id;
	private URI				goblintInput;
	private boolean 		ready;
	private boolean 		failure;
	private URI				result;
	
    @OslcDescription("The Goblint input.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "goblintInput")
    @OslcReadOnly
    @OslcTitle("input")
    public URI getGoblintInput() {
		return goblintInput;
	}
    
	public void setGoblintInput(URI goblintInput) {
		this.goblintInput = goblintInput;
	}

    @OslcDescription("Goblint Output Identifier.")
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
	
	@OslcDescription("Resource ready.")
	@OslcOccurs(Occurs.ZeroOrOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "ready")
    @OslcReadOnly
    @OslcTitle("ready")
    public boolean getReady() {
		return ready;
	}
    
	public void setReady(boolean b) {
		this.ready = b;
	}

	@OslcDescription("Resource could not be generated.")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "failure")
    @OslcReadOnly
    @OslcTitle("failure")
	public boolean getFailure() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}

	@OslcDescription("Result of the Analysis.")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcPropertyDefinition(Constants.GOBLINT_NAMESPACE + "result")
    @OslcReadOnly
    @OslcTitle("result")
	public URI getResult() {
		return result;
	}

	public void setResult(URI goblintResult) {
		this.result = goblintResult;
	}
	
}
