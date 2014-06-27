import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AppMemory extends Orchestrator {
	
	public AppMemory(){
		String[]b=new String[2];

		lerBaseDados(b);
		
		System.exit(0);
		
		HashMap<String,Double> map = new HashMap<String,Double>();
		ValueComparator bvc =  new ValueComparator(map);
		TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);

		map.put("A",99.5);
		map.put("B",67.4);
		map.put("C",67.4);
		map.put("D",67.3);

		System.out.println("unsorted map: "+map);

		sorted_map.putAll(map);

		System.out.println("results: "+sorted_map);
	}

    public static void main(String[] args) {
    
    	new AppMemory();
    	

       
    }

	@Override
	public String[] ordenar(String[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] lerBaseDados(String[] a) {
		
		/*SAXParserCPU parser = new SAXParserCPU();
		Arquivos arquivo = new Arquivos();
		//System.out.println("Passei por aqui");
		java.util.ArrayList lista = parser.processar("getCPU", arquivo.getCaminhoBaseDados());
		java.util.Iterator itr = lista.iterator();
		while(itr.hasNext()){
			Object element = itr.next();
			System.out.print(element + " ");			
		}//fim while
		
		return null;
	}
    */
	DOMMemoryParser parser = new DOMMemoryParser();
	parser.readXML();
	return null;
   
}

class ValueComparator implements Comparator<String> {

    Map<String, Double> base;
    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
}
