package br.pucpr.cg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.lang.Math.toIntExact;

public class Parser {
	
	private final static String PATH = "config.json";
	public int MSF;
	public int TTH;
	public int TMV;
	public int TMD;
	public String fx;
	JSONParser parser; 
	
	public Parser(){
		parser = new JSONParser();
		MSF = -1;
		TTH = -1;
		TMV = -1;
		TMD = -1;
		fx = null;
	}
	
	public Parser parser() throws FileNotFoundException, ParseException, IOException{
		JSONObject json = (JSONObject) parser.parse(new FileReader(PATH));

	    MSF = toIntExact((long)json.get("MAX_SMOOTH_FILTER"));
		TTH = toIntExact((long)json.get("TERRAIN_TRESHOLD"));
	    TMV = toIntExact((long)json.get("TERRAIN_MIN_VALUE"));
	    TMD = toIntExact((long)json.get("TERRAIN_MAX_DIFFERENCE"));
	    fx =  (String) json.get("POST_FX");
	    
	    System.out.println(MSF + " - " +  TTH + " - " + TMV + " - " +  TMD);
	    return this;
	}
	
}
