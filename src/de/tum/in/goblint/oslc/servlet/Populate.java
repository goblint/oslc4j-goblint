package de.tum.in.goblint.oslc.servlet;

import java.net.URI;
import java.net.URISyntaxException;

import de.tum.in.goblint.oslc.GoblintInput;
import de.tum.in.goblint.oslc.GoblintOutput;
import de.tum.in.goblint.oslc.Persistence;


final class Populate
{
    private final String basePath;
    private final URI    serviceProviderURI;

    public Populate(final String  basePath,
                    final URI     serviceProviderURI)
    {
        super();

        this.basePath           = basePath;
        this.serviceProviderURI = serviceProviderURI;
    }

    public void fixup()
    {
        final GoblintInput[] GoblintInputs = Persistence.getInputs();

        for (final GoblintInput GoblintInput : GoblintInputs)
        {
            GoblintInput.setServiceProvider(serviceProviderURI);
        }
    }

    public void populate()
           throws URISyntaxException
    {
    	System.out.print("populate()\n");
    	GoblintInput i1 = createGoblintInput(10,"http://localhost/~kalmera/proj1/files","http://localhost/~kalmera/proj1/conf.json","main.c");
        persistGoblintInput(i1);
    	GoblintInput i2 = createGoblintInput(20,"http://localhost:8080/files/proj2/","http://localhost:8080/files/conf2.json","test.c");
        persistGoblintInput(i2);
        GoblintOutput o1 = createGoblintOutput(40, "http://localhost:8080/oslj4j-goblint/goblint/input/10", false, true);
        persistGoblintOutput(o1);
        
    }



    private static GoblintInput createGoblintInput(
    		int       id,
    	    String    fileBaseURI,
    	    String    confFile,
    		String    callString
    	) throws URISyntaxException
    {
        final GoblintInput gi = new GoblintInput();

        gi.setId(id);
        gi.setFileBaseURI(new URI(fileBaseURI));
        gi.setConfFile(new URI(confFile));
        gi.setCallString(callString);

        return gi;
    }

    private static GoblintOutput createGoblintOutput(
    		int          id,
    	    String		 input,
    	    boolean      ready,
    		boolean      failure
    	) throws URISyntaxException
    {
        final GoblintOutput go = new GoblintOutput();

        go.setId(id);
        go.setGoblintInput(new URI(input));
        go.setReady(ready);
        go.setFailure(failure);

        return go;
    }

    private void persistGoblintInput(final GoblintInput input)
            throws URISyntaxException
    {
        final int identifier = input.getId();

        final URI about = new URI(basePath + "/goblint/input/" + identifier);

        input.setAbout(about);
        input.setServiceProvider(serviceProviderURI);

        Persistence.addInput(input);
    }
    
    private void persistGoblintOutput(final GoblintOutput output)
            throws URISyntaxException
    {
        final int identifier = output.getId();

        final URI about = new URI(basePath + "/goblint/output/" + identifier);

        output.setAbout(about);
        Persistence.addOutput(output);
    }
}
