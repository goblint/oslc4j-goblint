package de.tum.in.goblint.oslc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class GoblintRun extends Thread
{
	private GoblintOutput output;
	
	
	public GoblintRun(GoblintOutput ou){
		output = ou;
	}
	
	public void run() {
		GoblintInput input = null;
		
		while (input==null) {
			URI inputURI = output.getGoblintInput();
			if (inputURI != null) {
				System.out.printf("input = %s\n", inputURI.toString());
				input = Persistence.getInput(inputURI.toString());
			}
			if (input==null)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
		}
		
		System.out.printf("input = %s\n", input.toString());
		
		File 	   idir = new File(Paths.getInputPath() + File.separatorChar + input.getId());
		File 	   odir = new File(Paths.getOutputPath() + File.separatorChar + output.getId());
		int depth =  input.getFileBaseURI().getPath().split("/").length;
		String wget     = "/opt/local/bin/wget -nH -r --no-parent --reject index.html* --cut-dirs=" + depth+" " + input.getFileBaseURI().toString()+"/" ;
		String goblint  = Paths.getGoblintBinPath() + File.separatorChar + "goblint --sets result indented " + input.getCallString() + " -o " + odir.getAbsolutePath() + File.separatorChar + "result.xml" ;
		
		
		output.setReady(false);
		try {
			if (!(idir.exists())) {
				idir.mkdirs();
				System.out.printf("exec:%s\n",wget);
				Process p = Runtime.getRuntime().exec(wget,null,idir);
				/*try {
					BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					String line = null;  
		            while ((line = in.readLine()) != null) {  
		                System.out.printf(">%s\n",line);  
		            }  
				} catch (IOException e) {}*/
				if (p.waitFor() != 0) {
					output.setFailure(true);
					output.setReady(true);
					return;
				}
			}
			
			if (!(odir.exists())) {
				odir.mkdirs();
			}

			System.out.printf("exec:%s\n",goblint);
			Process p2 = Runtime.getRuntime().exec(goblint,null,idir);
			try {
			BufferedReader in = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			String line = null;  
            while ((line = in.readLine()) != null) {  
                System.out.printf(">%s\n",line);  
            }  
			} catch (IOException e) {}
			if (p2.waitFor() != 0) {
				output.setFailure(true);
			}
		} catch (IOException e) {
			output.setFailure(true);
			e.printStackTrace();
		} catch (InterruptedException e) {
			output.setFailure(true);
			e.printStackTrace();
		}
		if (!output.getFailure()) {
			try {
				output.setResult(new URI(Paths.getOutputUrl().toString()+output.getId()+"/result.xml"));
			} catch (URISyntaxException e) {
				output.setFailure(true);
			}
		}
		output.setReady(true);
	}
	

}
