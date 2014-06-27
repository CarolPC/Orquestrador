      
    import java.io.File;  
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
      
    public class DOMDiskParser {  
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
         
         String disk_used = host.getAttribute("O_DISK_USED");
         //System.out.println(porcentagem);
         

         mapa.put(nome, disk_used);  
         Set<String> chaves = mapa.keySet();  
         for (String chave : chaves)  
         {  
             if(chave != null)  
            
             	System.out.println(chave + " = " + mapa.get(chave));
         }  
         
         System.out.println("Host name : "  
                 + host.getAttribute("name")); 
         System.out.println("Total Disk : "  
           + host.getAttribute("O_DISK_TOTAL"));  
         System.out.println("Used Disk : "  
                 + host.getAttribute("O_DISK_USED"));  
        
      
        }  
       }  
      } catch (Exception e) {  
       e.printStackTrace();  
      }  
     }  
    }  

