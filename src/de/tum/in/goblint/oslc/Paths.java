package de.tum.in.goblint.oslc;

public class Paths {
	private static String inputPath      = "/Users/kalmera/tmp/inputs";
	private static String outputPath     = "/Users/kalmera/Sites/output";
	private static String goblintBinPath = "/Volumes/goblint_stuff/analyzer";
	private static String persistence    = "/Users/kalmera/tmp";
	private static String outputUrl		 = "http://localhost/~kalmera/output/";
	
	static String getInputPath(){
		return inputPath;
	}
	static String getOutputPath(){
		return outputPath;
	}
	static String getGoblintBinPath(){
		return goblintBinPath;
	}
	static String getPersistencePath(){
		return persistence;
	}
	public static String getOutputUrl() {
		return outputUrl;
	}
}
