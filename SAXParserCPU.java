



import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXParserCPU {

	private String TOKEN_ID="0";

	private String TENANT_ID="0";

	private String HOSTS_ID="0";

	private String SERVERS_ID="0";

	public String getTokenID(){

		return TOKEN_ID;

	}//fim getTokenID

	public String getTenantID(){

		return TENANT_ID;

	}//fim getTenantID

	public String getHostsID(){

		return HOSTS_ID;

	}//fim geHostsID

	public String getServersID(){

		return SERVERS_ID;

	}//fim getServersID

	public void setTokenID(String token){
		TOKEN_ID=token;
	}//fim setTokenID

	public void setTenantID(String tenant){
		TENANT_ID=tenant;
	}//fim setTokenID

	public void setHostsID(String hosts){
		HOSTS_ID=hosts;
	}//fim setHostsID

	public void setServersID(String servers){
		SERVERS_ID=servers;
	}//fim setTokenID

	//Apenas para testes
	boolean ARQUIVO_HOSTS_FIXO=true;

	//Construtor
	public SAXParserCPU(){

		//Apenas para testes
		if(ARQUIVO_HOSTS_FIXO){
			//Busca pela tag <host ... /> no arquivo XML
			//
			//Uso uma lista porque nao sei quantos hosts podem ser retornados
			java.util.ArrayList listaHosts = processar("getCPU", "basedados.xml"); //
			java.util.Iterator itr = listaHosts.iterator();
			while(itr.hasNext()){
				Object element = itr.next();
				//System.out.print(element + " ");			
			}//fim while
		}//fim if

	}//fimdoconstrutor

	public java.util.ArrayList processar(String tipo, String arquivo){

		java.util.ArrayList lista = new java.util.ArrayList();

		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler(tipo);
			//Apenas para testes			
			/*if(ARQUIVO_HOSTS_FIXO){
				System.out.println("[[["+arquivo+"]]]");//Como processar o xml que estah na memoria???
				parser.parse(new File(arquivo), handler);
			} else {
				// convert String into InputStream
				InputStream is = new ByteArrayInputStream(arquivo.getBytes());
				parser.parse(is, handler);
			}//fim else
			*/
			//Printing the list obtained from XML
			//System.out.println("Passei por aqui");
			parser.parse(new File(arquivo), handler);
			for ( Tag doc : handler.docList){
			
				if (tipo.equals("getCPU"))
					//Armazena apenas os objetos cujo hosts sao do tipo 'compute'
					if(doc.name.equals("name")){
						System.out.println(doc.name);
						lista.add(doc.name);
					}//fim if

			}//fim for
		} catch (Exception e){
			e.printStackTrace();
		}

		return lista;

	}//fim processar


	/**
	 * The Handler for SAX Events.
	 */	 
	class SAXHandler extends DefaultHandler {

		String TIPO = "0";

		public SAXHandler(String tipo){
			TIPO = tipo;
		}//fimSaxHandler

		public String getTipo(){
			return TIPO;
		}//fimGetTipo

		//Importante: docList e doc sao globais (variaveis de instancia)
		//
		//docList 'guarda' uma lista de objetos 'doc'
		List<Tag> docList = new ArrayList<>();
		Tag doc = null; //1 objeto do tipo Tag, que contem varios campos (doc.id, doc.name, doc.expires,...) 
		//String content = null;

		@Override
		//Disparado quando a tag de inicio eh encontrada
		public void startElement(String uri, String localName, 
				String qName, Attributes attributes) 
						throws SAXException {

			
			String tipo = getTipo();



			
			if(tipo.equals("getCPU")){
			
				switch(qName.toLowerCase()){ //tag atual. Ex.: <host...>
			
				case "host":
					doc = new Tag(); //Cria um novo objeto para guardar o valor
					doc.name = attributes.getValue("name"); //Ex.: <host ... service="..." .../>
					System.out.println("\tHOST_NAME:\t" + doc.name);
					//System.out.println("\tSERVICE:\t" + doc.service);
					break;

				default:
					break;

				}//fim do switch

			}//fim if

		}//fim startElement

		//Disparado quando a tag de fim eh encontrada
		//
		//Nota: Eh necessario definir 'cada' tipo de qname aqui tb.
		@Override
		public void endElement(String uri, String localName, 
				String qName) throws SAXException {

			String tipo = getTipo();


			if(tipo.equals("getCPU")){

				switch(qName){ //tag atual. Ex.: <host...>

				case "name":
					docList.add(doc);
					break;
				default:
					break;
				}//fim switch
			}//fim if

		}//fim endElement

		//Trata campos dentro da 'hierarquia' da tag
		//Ex.: <host>
		//        <nome1>...</nome1>
		//        <nome2>...</nome2>
		//     </host>
		//Nota: nao utilizamos esse metodo
		/*@Override
		public void characters(char[] ch, int start, int length) 
				throws SAXException {
			System.out.println("Passei por aqui"); 
			//content = String.copyValueOf(ch, start, length).trim();
		}//fim characters
		 */

	}//fim classe SaxHandler

	//Essa classe cria um objeto do tipo Tag. 
	//O objeto possui 'campos' que serao utilizados para guardar os valores do arquivo XML
	//Ex.: tag.id
	//     tag.name
	//     tag.expires....
	class Tag {

		String id="";
		String name="";


	}//fim classe interna

	//Inicia a classe
	public static void main(String args[]){
		new SAXParserCPU();

	}//finalMain

}//fim classe

