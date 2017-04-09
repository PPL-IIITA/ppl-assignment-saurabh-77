import java.util.*;
import java.io.*;

public class Main1{
	public static void main(String args[]) throws Exception{
                System.out.println("Total 50 boys and 150 girls\n");
//		Generator gen = new Generator();
//		gen.boy_generator();
//		gen.girl_generator();
		
		//read boys data from file and store it to blist arraylist;
		BufferedReader br = null;
        String line = "";
        ArrayList<Boy> blist = new ArrayList<Boy>();
		try {
			
            br = new BufferedReader(new FileReader("boy.csv"));
            while ((line = br.readLine()) != null) {
				Boy b = new Boy();
                
                String[] by = line.split(",");
				b.setName(by[0]);
				b.setAttractiveness(Integer.parseInt(by[1]));
				b.setIntellegence(Integer.parseInt(by[2]));
				b.setBudget((float)Double.parseDouble(by[3]));
				b.setStatus("single");
				b.setType(by[4]);
				blist.add(b);
            }

        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (IOException e) {
            e.printStackTrace();
        }
		
		//read girls data from file and store it to glist arraylist;
	
        ArrayList<Girl> glist = new ArrayList<Girl>();
		try {
			
            br = new BufferedReader(new FileReader("girl.csv"));
            while ((line = br.readLine()) != null) {
				Girl g = new Girl();
                String[] by = line.split(",");
				g.setName(by[0]);
				g.setAttractiveness(Integer.parseInt(by[1]));
				g.setIntellegence(Integer.parseInt(by[2]));
				g.setMainentence((float)Double.parseDouble(by[3]));
				char c[] = by[4].toCharArray();
				g.setBoytype(c[0]);
				g.setType(by[5]);
				glist.add(g);
            }

        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (IOException e) {
            e.printStackTrace();
        }
		
		for(Girl g : glist){
			char ch =  g.boytype;
			Boy by = null;
			int max=0;
			switch(ch){
					
					case 'I':
						for(Boy b : blist){
							if(b.intellegence >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = b.intellegence;
								by.setStatus("commited");
							
							}
						}
						break;
					case 'A':
						for(Boy b : blist){
							if(b.attractiveness >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = b.attractiveness;
								by.setStatus("commited");
							
							}
						}
						break;
					case 'R':
						for(Boy b : blist){
							if((int)b.budget >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = (int)b.budget;
								by.setStatus("commited");
								
							}
						}
						break;
						
						
						
			}
			if(by!=null){
				System.out.printf("%s\t%s\n", g.name, by.name);
			}
		}
	}
	
	
}