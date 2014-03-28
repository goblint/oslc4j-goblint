package de.tum.in.goblint.oslc.resources;

import de.tum.in.goblint.oslc.Constants;
import de.tum.in.goblint.oslc.GoblintRun;
import de.tum.in.goblint.oslc.Persistence;
import de.tum.in.goblint.oslc.Utilities;
import de.tum.in.goblint.oslc.definitions.*;
import org.apache.wink.json4j.compat.JSONException;
import org.eclipse.lyo.oslc4j.core.annotation.*;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@OslcService(Constants.GOBLINT_DOMAIN)
@OslcName("GoblintResource")
@OslcResourceShape(title = "The Goblint OSLC resource", describes = {"goblint:GoblintResource"})
@Path("goblint")
public class GoblintResource {

    // Thread pool for file processing tasks
    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @OslcCreationFactory
            (
                    title = "Goblint ConfFile Factory",
                    label = "Goblint ConfFile Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:ConfFileAsset", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("createConfFile")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createConfFile(@Context final HttpServletRequest httpServletRequest,
                                   final ConfFileAsset input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/confFiles/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addConfFile(input);

        return Response.created(about).entity(input).build();
    }

    @OslcQueryCapability
    (
        title = "confFiles Query Capability",
        label = "confFiles Catalog Query",
        resourceShape = OslcConstants.PATH_RESOURCE_SHAPES + "/confFiles" ,
        resourceTypes = {"goblint:confFiles"},
        usages = {OslcConstants.OSLC_USAGE_DEFAULT}
    )

    @GET
    @Path("confFiles")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:confFiles")
    @OslcTitle("confFiles")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<ConfFileAsset> getConfFiles()
            throws JSONException {
        return Persistence.getConfFileAssets();
    }

    public void setConfFiles(Collection<ConfFileAsset> f){
    }

    @GET
    @Path("confFiles/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public ConfFileAsset getInput(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getConfFileAssets(inputId);
    }


    @OslcCreationFactory
            (
                    title = "Goblint SourceFolder Factory",
                    label = "Goblint SourceFolder Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:SourceFolderAsset", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("createSourceFolders")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createSourceFolder(@Context final HttpServletRequest httpServletRequest,
                                       final SourceFolderAsset input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/sourceFolders/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addSourceFolder(input);

        return Response.created(about).entity(input).build();
    }

    @GET
    @Path("sourceFolders")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:sourceFolders")
    @OslcTitle("sourceFolders")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<SourceFolderAsset> getSourceFolders()
            throws JSONException {
        return Persistence.getSourceFolderAssets();
    }

    public void setSourceFolders(Collection<SourceFolderAsset> f){
        }


    @GET
    @Path("sourceFolders/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public ConfFileAsset getSourceFolders(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getSourceFolderAssets(inputId);
    }


    @OslcCreationFactory
            (
                    title = "Goblint ResultWarning Factory",
                    label = "Goblint ResultWarning Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:ResultWarning", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("createResultWarning")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createStoredInvariant(@Context final HttpServletRequest httpServletRequest,
                                          final GoblintResultWarning input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/resultWarnings/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addGoblintResultWarning(input);

        return Response.created(about).entity(input).build();
    }

    @GET
    @Path("resultWarnings")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:resultWarnings")
    @OslcTitle("resultWarnings")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<GoblintResultWarning> getResultWarnings()
            throws JSONException {
        return Persistence.getGoblintResultWarnings();
    }

    public void setResultWarnings(Collection<GoblintResultWarning> f){
        }

    @GET
    @Path("resultWarnings/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintResultWarning getGoblintResultWarning(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getGoblintResultWarnings(inputId);
    }

    @OslcCreationFactory
            (
                    title = "Goblint ResultXML Factory",
                    label = "Goblint ResultXML Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:ResultXML", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("createResultXML")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createStoredInvariant(@Context final HttpServletRequest httpServletRequest,
                                          final GoblintResultXML input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/resultXML/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addGoblintResultXML(input);

        return Response.created(about).entity(input).build();
    }

    @GET
    @Path("resultXML")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:resultXML")
    @OslcTitle("resultXML")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<GoblintResultXML> getResultXML()
            throws JSONException {
        return Persistence.getGoblintResultXML();
    }

    public void setResultXML(Collection<GoblintResultXML> f){
        }

    @GET
    @Path("resultXML/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintResultXML getGoblintResultXML(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getGoblintResultXML(inputId);
    }


    @OslcCreationFactory
            (
                    title = "Goblint ResultHTML Factory",
                    label = "Goblint ResultHTML Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:ResultHTML", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("createResultHTML")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createStoredInvariant(@Context final HttpServletRequest httpServletRequest,
                                          final GoblintResultHTML input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/resultHTML/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addGoblintResultHTML(input);

        return Response.created(about).entity(input).build();
    }

    @GET
    @Path("resultHTML")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:resultHTML")
    @OslcTitle("resultHTML")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<GoblintResultHTML> getResultHTML()
            throws JSONException {
        return Persistence.getGoblintResultHTML();
    }

    public void setResultHTML(Collection<GoblintResultHTML> f){
        }

    @GET
    @Path("resultHTML/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintResultHTML getGoblintResultHTML(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getGoblintResultHTML(inputId);
    }


    @OslcCreationFactory
            (
                    title = "Goblint ResultStoredInvariant Factory",
                    label = "Goblint ResultStoredInvariant Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:ResultStoredInvariant", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("createResultStoredInvariant")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createStoredInvariant(@Context final HttpServletRequest httpServletRequest,
                                          final GoblintResultStoredInvariant input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/resultStoredInvariants/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addGoblintResultStoredInvariants(input);

        return Response.created(about).entity(input).build();
    }

    @GET
    @Path("resultStoredInvariants")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:resultStoredInvariants")
    @OslcTitle("resultStoredInvariants")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<GoblintResultStoredInvariant> getResultStoredInvariants()
            throws JSONException {
        return Persistence.getGoblintResultStoredInvariants();
    }

    public void setResultStoredInvariants(Collection<GoblintResultStoredInvariant> f){
        }


    @GET
    @Path("resultStoredInvariants/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintResultStoredInvariant getGoblintResultStoredInvariants(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getGoblintResultStoredInvariants(inputId);
    }


    @OslcCreationFactory
            (
                    title = "Goblint Analysis Job Factory",
                    label = "Goblint Analysis Job Creation",
                    resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
                    resourceTypes = {"goblint:AnalysisJob", "http://open-services.net/ns/asset#Asset"},
                    usages = {OslcConstants.OSLC_USAGE_DEFAULT}
            )
    @POST
    @Path("runAnalysisJob")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response createStoredInvariant(@Context final HttpServletRequest httpServletRequest,
                                          final GoblintAnalysisJob input)
            throws URISyntaxException {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                null,
                httpServletRequest.getServerName(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getContextPath() + "/goblint/resultStoredInvariants/" + identifier,
                null,
                null);

        input.setAbout(about);
        input.id = identifier;

        Persistence.addGoblintAnalysisJobs(input);

        executorService.submit(new GoblintRun(input));

        return Response.created(about).entity(input).build();
    }

    @GET
    @Path("analysisJobs")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcPropertyDefinition("goblint:analysisJobs")
    @OslcTitle("analysisJobs")
    @OslcValueType(ValueType.LocalResource)
    @OslcReadOnly
    public java.util.Collection<GoblintAnalysisJob> getAnalysisJobs()
            throws JSONException {
        return Persistence.getGoblintAnalysisJobs();
    }

    public void setAnalysisJobs(Collection<GoblintAnalysisJob> f){
        }

    @GET
    @Path("analysisJobs/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintAnalysisJob getGoblintAnalysisJobs(@PathParam("inputId") int inputId)
            throws JSONException {
        return Persistence.getGoblintAnalysisJobs(inputId);
    }

}

