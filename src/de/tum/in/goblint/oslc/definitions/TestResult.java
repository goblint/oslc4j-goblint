package de.tum.in.goblint.oslc.definitions;

import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@OslcNamespace("http://open-services.net/ns/qm#")
@OslcResourceShape(title = "OSLC Test Result", describes = "http://open-services.net/ns/qm#TestResult")
public class TestResult extends OSLCObject {

    private List<URI> reportsOnTestPlan;
    private URI reportsOnTestCase;
    private String status;
    private List<URI> affectedByChangeRequest;
    private List<URI> executesTestScript;
    private List<URI> producedByTestExecutionRecord;

    public TestResult() throws URISyntaxException {
        super();
        addType(new URI("http://open-services.net/ns/qm#TestResult"));
    }

    @OslcPropertyDefinition("oslc-qm:status")
    @OslcTitle("status")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcDescription("Used to indicate the state of the Test Result based on values defined by the service provider. Most often a read-only property.")
    public String getStatus(){
        return status;
    }

    @OslcPropertyDefinition("oslc-qm:affectedByChangeRequest")
    @OslcTitle("affectedByChangeRequest")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Change request that affects the Test Result. It is likely that the target resource will be an oslc_cm:ChangeRequest but that is not necessarily the case.")
    public List<URI> getAffectedByChangeRequest(){
        return affectedByChangeRequest;
    }
    @OslcPropertyDefinition("oslc-qm:executesTestScript")
    @OslcTitle("executesTestScript")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Test Script executed to produce the Test Result. It is likely that the target resource will be an oslc-qm:TestScript but that is not necessarily the case.")
    public List<URI> getExecutesTestScript(){
        return executesTestScript;
    }

    @OslcPropertyDefinition("oslc-qm:producedByTestExecutionRecord")
    @OslcTitle("producedByTestExecutionRecord")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Test Execution Record that the Test Result was produced by. It is likely that the target resource will be an oslc-qm:TestExecutionRecord but that is not necessarily the case.")
    public List<URI> getProducedByTestExecutionRecord(){
        return producedByTestExecutionRecord;
    }

    @OslcPropertyDefinition("oslc-qm:reportsOnTestCase")
    @OslcTitle("reportsOnTestCase")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcDescription("Test Case that the Test Result reports on. It is likely that the target resource will be an oslc-qm:TestCase but that is not necessarily the case.")
    public URI getReportsOnTestCase(){
        return reportsOnTestCase;
    }

    @OslcPropertyDefinition("oslc-qm:reportsOnTestPlan")
    @OslcTitle("reportsOnTestPlan")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcDescription("Test Plan that the Test Result reports on. It is likely that the target resource will be an oslc-qm:TestPlan but that is not necessarily the case.")
    public List<URI> getReportsOnTestPlan(){
        return reportsOnTestPlan;
    }

    public void setReportsOnTestPlan(List<URI> reportsOnTestPlan) {
        this.reportsOnTestPlan = reportsOnTestPlan;
    }

    public void setReportsOnTestCase(URI reportsOnTestCase) {
        this.reportsOnTestCase = reportsOnTestCase;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAffectedByChangeRequest(List<URI> affectedByChangeRequest) {
        this.affectedByChangeRequest = affectedByChangeRequest;
    }

    public void setExecutesTestScript(List<URI> executesTestScript) {
        this.executesTestScript = executesTestScript;
    }

    public void setProducedByTestExecutionRecord(List<URI> producedByTestExecutionRecord) {
        this.producedByTestExecutionRecord = producedByTestExecutionRecord;
    }
}
