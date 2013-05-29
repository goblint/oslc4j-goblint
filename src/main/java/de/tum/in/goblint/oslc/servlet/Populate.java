package de.tum.in.goblint.oslc.servlet;

import java.net.URI;
import java.net.URISyntaxException;

import de.tum.in.goblint.oslc.GoblintInput;
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
        persistGoblintInput(createGoblintInput(10,"http://localhost:8080/files/proj1/","http://localhost:8080/files/conf1.json","main.c"));
        persistGoblintInput(createGoblintInput(20,"http://localhost:8080/files/proj2/","http://localhost:8080/files/conf2.json","test.c"));
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

    private void persistGoblintInput(final GoblintInput input)
            throws URISyntaxException
    {
        final int identifier = input.getId();

        final URI about = new URI(basePath + "/goblint/inputs/" + identifier);

        input.setAbout(about);
        input.setId(identifier);
        input.setServiceProvider(serviceProviderURI);

        Persistence.addInput(input);
    }
}
