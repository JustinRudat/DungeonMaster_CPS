package dungeonMaster.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.services.EnvironmentService;

public class MapWriter {
	public void saveTheMap(EnvironmentService env,String name) throws IOException {
		Cell[][] plateau = env.getPlateau();
		File file = new File(name);
		if(!file.exists()) {
			file.createNewFile();
		}
		
		PrintWriter pw = new PrintWriter(file);
		pw.println("<map width="+env.getWidth()+" height="+env.getHeight()+">");
		for(int i=0;i<env.getWidth();i++) {
			for(int j=0;j<env.getHeight();j++) {
				Cell c= plateau[i][j];
				pw.println("<cell type=\""+c+"\" col ="+i+" row="+j+"/>");
			}
		}
		pw.println("</map>");
		pw.flush();
	}
	
	public EnvironmentService loadMap(String nom_fichier) {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		EnvironmentService env = new EnvironmentImplem();
		try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document= builder.parse(new File(nom_fichier));
            final Element racine = document.getDocumentElement();
            int width_str = Integer.parseInt(racine.getAttribute("width"));
            int height_str = Integer.parseInt(racine.getAttribute("height"));
            Cell[][] cells = new Cell[width_str][height_str];
            env.init(width_str,height_str);
            final NodeList racineNoeuds = racine.getChildNodes();
            for(int i=0;i<width_str;i++) {
            	for(int j=0;j<height_str;j++) {
            		Element elm =  (Element) racineNoeuds.item(i+width_str*j);
            		Cell test = null;
            		switch(elm.getAttribute("type")) {
            		case "WLL" : 
            			test = Cell.WLL;
            			break;
            		case "DNO" :
            			test = Cell.DNO;
            			break;
            		case "DWO" :
            			test=Cell.DWO;
            			break;
            		case "DNL" :
            			test = Cell.DNL;
            			break;
            		case "DWL" :
            			test=Cell.DWL;
            			break;
            		case "DNC" :
            			test = Cell.DNC;
            			break;
            		case "IN" :
            			test = Cell.IN;
            			break;
            		case "OUT" :
            			test= Cell.OUT;
            			break;
            		case "EMP" : 
            			test = Cell.EMP;
            			break;
            		case "DWC" :
            			test=Cell.DWC;
            			break;
            		}
            		cells[i][j]=test;
            	}
            }
            env.setPlateau(cells);
            
		}catch(Exception e) {
			e.printStackTrace();
		}
		return env;
	}
}
