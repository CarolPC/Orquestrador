    import java.util.HashMap;  
    import java.util.Set;  
      
    public class Teste  
    {  
      
        public static void main(String[] args)  
        {  
            HashMap<String, String> mapa = new HashMap<String, String>();  
            mapa.put("Diegoo", " Ricardo");  
            mapa.put(null, "Teste");  
            mapa.put(null, "Outro Teste");  
            mapa.put("Diego", " ;)");  
            Set<String> chaves = mapa.keySet();  
            for (String chave : chaves)  
            {  
                if(chave != null)  
                    System.out.println(chave + mapa.get(chave));  
            }  
        }     
    }                           
