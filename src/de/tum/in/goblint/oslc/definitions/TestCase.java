package de.tum.in.goblint.oslc.definitions;

import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@OslcNamespace("http://open-services.net/ns/qm#")
@OslcResourceShape(title = "OSLC Test Case", describes = "http://open-services.net/ns/qm#TestCase")
public class TestCase extends OSLCObject {
    private List<URI> relatedChangeRequest;
    private List<URI> testsChangeRequest;
    private List<URI> usesTestScript;
    private List<URI> validatesRequirement;

    public TestCase() throws URISyntaxException {
        super();
        relatedChangeRequest = new ArrayList<>();
        testsChangeRequest = new ArrayList<>();
        usesTestScript = new ArrayList<>();
        validatesRequirement = new ArrayList<>();
        addType(new URI("http://open-services.net/ns/qm#TestCase"));
    }

    @OslcPropertyDefinition("oslc-qm:relatedChangeRequest")
    @OslcTitle("relatedChangeRequest")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("A related change request. It is likely that the target resource will be an oslc_cm:ChangeRequest but that is not necessarily the case.")
    public List<URI> getRelatedChangeRequest() {
        return relatedChangeRequest;
    }

    @OslcPropertyDefinition("oslc-qm:testsChangeRequest")
    @OslcTitle("testsChangeRequest")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Change Request tested by the Test Case. It is likely that the target resource will be an oslc_cm:ChangeRequest but that is not necessarily the case.")
    public List<URI> getTestsChangeRequest() {
        return testsChangeRequest;
    }

    @OslcPropertyDefinition("oslc-qm:usesTestScript")
    @OslcTitle("usesTestScript")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Test Script used by the Test Case. It is likely that the target resource will be an oslc-qm:TestScript but that is not necessarily the case.")
    public List<URI> getUsesTestScript() {
        return usesTestScript;
    }

    @OslcPropertyDefinition("oslc-qm:validatesRequirement")
    @OslcTitle("validatesRequirement")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Requirement that is validated by the Test Case. It is likely that the target resource will be an oslc_rm:Requirement but that is not necessarily the case.")
    public List<URI> getValidatesRequirement() {
        return validatesRequirement;
    }

    public void setRelatedChangeRequest(List<URI> relatedChangeRequest) {
        this.relatedChangeRequest = relatedChangeRequest;
    }

    public void setTestsChangeRequest(List<URI> testsChangeRequest) {
        this.testsChangeRequest = testsChangeRequest;
    }

    public void setUsesTestScript(List<URI> usesTestScript) {
        this.usesTestScript = usesTestScript;
    }

    public void setValidatesRequirement(List<URI> validatesRequirement) {
        this.validatesRequirement = validatesRequirement;
    }
}
