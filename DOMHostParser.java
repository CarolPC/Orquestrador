      
    import java.io.File;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
    public class DOMHostParser {  
     public void readXML() {  
      try {  
      
       File xmlFile = new File("basedados.xml");  
       DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
         .newInstance();  
       DocumentBuilder documentBuilder = documentFactory  
         .newDocumentBuilder();  
       Document doc = documentBuilder.parse(xmlFile);  
      
       doc.getDocumentElement().normalize();  
       NodeList nodeList = doc.getElementsByTagName("HOST");  
       
       HashMap<String, String> mapa = new HashMap<String, String>();  
      
       //System.out.println("Root element :"  
         //+ doc.getDocumentElement().getNodeName());  
      
       for (int temp = 0; temp < nodeList.getLength(); temp++) {  
        Node node = nodeList.item(temp);  
      
        //System.out.println("\nElement type :" + node.getNodeName()); 
        System.out.println("\n");
      
        if (node.getNodeType() == Node.ELEMENT_NODE) {  
      
         Element host = (Element) node;  
         
         
         String nome = host.getAttribute("name");
         //System.out.println(nome);
         
         String porcentagem = host.getAttribute("H_CPU");
         //System.out.println(porcentagem);
        
         mapa.put(nome, porcentagem);  
         Set<String> chaves = mapa.keySet();  
         for (String chave : chaves)  
         {  
             if(chave != null)  
            
             	System.out.println(chave + " = " + mapa.get(chave));
         }  
         /*System.out.println("Host name : "  
           + host.getAttribute("name"));  
         System.out.println("Percentage : "  
                 + host.getAttribute("H_CPU"));  
         System.out.println("Total Cores : "  
                 + host.getAttribute("O_CPU_TOTAL")); 
         System.out.println("Cores Used : "  
                 + host.getAttribute("O_CPU_USED"));*/ 
      
        } //fimif 
       } //fimfor

      } //fimtry 
      catch (Exception e) {  
       e.printStackTrace();  
      }//fimcatch
	
	
	
     
     } //fim_metodo
    }  //fimclass
