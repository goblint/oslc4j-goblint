package de.tum.in.goblint.oslc.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.wink.json4j.JSONException;
import org.eclipse.lyo.oslc4j.core.annotation.OslcCreationFactory;
import org.eclipse.lyo.oslc4j.core.annotation.OslcService;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;

import de.tum.in.goblint.oslc.Constants;
import de.tum.in.goblint.oslc.GoblintInput;
import de.tum.in.goblint.oslc.GoblintOutput;
import de.tum.in.goblint.oslc.GoblintRun;
import de.tum.in.goblint.oslc.Persistence;
import de.tum.in.goblint.oslc.Utilities;

@OslcService(Constants.GOBLINT_DOMAIN)
@Path("goblint")
public class GoblintResource {

    @OslcCreationFactory
    (
         title = "Goblint Input Creation Factory",
         label = "Goblint Input Creation",
         resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
         resourceTypes = {Constants.TYPE_GOBLINT_INPUT},
         usages = {OslcConstants.OSLC_USAGE_DEFAULT}
    )
    @POST
    @Path("create_input")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response addInput(@Context final HttpServletRequest httpServletRequest,
                                      final GoblintInput       input)
           throws URISyntaxException
    {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                                  null,
                                  httpServletRequest.getServerName(),
                                  httpServletRequest.getServerPort(),
                                  httpServletRequest.getContextPath() + "/goblint/input/" + identifier,
                                  null,
                                  null);

        input.setAbout(about);
        input.setId(identifier);
        
        Persistence.addInput(input);

        return Response.created(about).entity(input).build();
    }

    @OslcCreationFactory
    (
         title = "Goblint Output Creation Factory",
         label = "Goblint Output Creation",
         resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + Constants.PATH_GOBLINT},
         resourceTypes = {Constants.TYPE_GOBLINT_INPUT},
         usages = {OslcConstants.OSLC_USAGE_DEFAULT}
    )
    @POST
    @Path("create_output")
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public Response addOutput(@Context final HttpServletRequest httpServletRequest,
                                       final GoblintOutput      output)
           throws URISyntaxException
    {
        final int identifier = Utilities.getNewId();

        final URI about = new URI(httpServletRequest.getScheme(),
                                  null,
                                  httpServletRequest.getServerName(),
                                  httpServletRequest.getServerPort(),
                                  httpServletRequest.getContextPath() + "/goblint/output/" + identifier,
                                  null,
                                  null);

        output.setAbout(about);
        output.setId(identifier);
        
        Persistence.addOutput(output);

        (new GoblintRun(output)).start();
        
        return Response.created(about).entity(output).build();
    }

    @GET
    @Path("input")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintInput[] getInputs()
           throws JSONException
    {
		return Persistence.getInputs();
    }

	@GET
    @Path("input/{inputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintInput getInput(@PathParam("inputId") int inputId)
           throws JSONException
    {
		return Persistence.getInput(inputId);
    }
	
	@GET
    @Path("output")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintOutput[] getOutputs()
           throws JSONException
    {
		return Persistence.getOutputs();
    }

	@GET
    @Path("output/{outputId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.TEXT_XML, OslcMediaType.APPLICATION_JSON})
    public GoblintOutput getOutput(@PathParam("outputId") int outputId)
           throws JSONException
    {
		return Persistence.getOutput(outputId);
    }
}

