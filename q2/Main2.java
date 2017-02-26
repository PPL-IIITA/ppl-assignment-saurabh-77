import java.util.*;
import java.io.*;

public class Main2{
	public static void main(String args[]) throws Exception{
		
		Generator gen = new Generator();
		gen.boy_generator();
		gen.girl_generator();
		gen.gift_generator();
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
		
		ArrayList<Couple> clist = new ArrayList<Couple>(); 
		
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
								b.setStatus("commited");
							}
						}
						break;
					case 'A':
						for(Boy b : blist){
							if(b.attractiveness >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = b.attractiveness;
								b.setStatus("commited");
							}
						}
						break;
					case 'R':
						for(Boy b : blist){
							if((int)b.budget >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = (int)b.budget;
								b.setStatus("commited");
							}
						}
						break;
						
						
						
			}
			
			if(by!=null){
//				System.out.printf("%s\t%s\n", g.name, by.name);
				Couple c = new Couple(g.name, by.name, (int)by.budget, (int)g.maintenence, by.type, g.type, g.intellegence);
				clist.add(c);
			}
		}
		//for(Couple c : clist)
		//	System.out.println(c.bname +"  " +c.gname);
	
		ArrayList<Gift> gif_list = new ArrayList<Gift>();
		try {
			
            br = new BufferedReader(new FileReader("gift.csv"));
            while ((line = br.readLine()) != null) {
				String str[] = line.split(",");
				Gift gif = new Gift(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), str[4]);
				gif_list.add(gif);
            }

        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (IOException e) {
            e.printStackTrace();
        }
		
		Collections.sort(gif_list, new Gifsort());
			
//		for(Gift g : gif_list){
//			System.out.println(g.name +" " +g.type+ "  "+ g.value+ " "+g.price);
//		}
		for(Couple c : clist){
			int sg = 0;
			int sb = 0, v = 0, lv = 0, i=0;
			//c.setTgift(0);
			switch(c.btype){
				case "miser" :
					while(sb < c.maintenence && i < 500){
						if(gif_list.get(i).left > 0){
							sb = sb + gif_list.get(i).price;
							v = v + gif_list.get(i).value;
							if(gif_list.get(i).type == "luxury"){
								lv = lv + gif_list.get(i).value;
							}
							gif_list.get(i).setLeft(gif_list.get(i).left - 1);
						}
						
						i++;
					}
					
					//c.setTgift(c.tgift +sb);
					c.setHappiness(c.budget - sb);
					break;
				case "generous":
					while(sb<c.budget && i<500){
						if(gif_list.get(i).left > 0){
							sb = sb + gif_list.get(i).price;
							v = v + gif_list.get(i).value;
							if(gif_list.get(i).type == "luxury"){
								lv = lv + gif_list.get(i).value;
							}
							gif_list.get(i).setLeft(gif_list.get(i).left - 1);
							
						}
						
						i++;
					}
					c.setHappiness(0);
					//c.setTgift(c.tgift +sb);
					break;
				case "geeks":
					while(sb<c.maintenence && i<500){
						if(gif_list.get(i).left > 0){
							sb = sb + gif_list.get(i).price;
							v = v + gif_list.get(i).value;
							if(gif_list.get(i).type == "luxury"){
								lv = lv + gif_list.get(i).value;
							}
							gif_list.get(i).setLeft(gif_list.get(i).left - 1);
						}
						
						i++;
					}
					while(gif_list.get(i).type != "luxury" && i<151){
						i++;
					}
					sb = sb + gif_list.get(i).price;
					gif_list.get(i).setLeft(gif_list.get(i).left - 1);
					lv = lv + gif_list.get(i).value;
					v = v + gif_list.get(i).value;
					//c.setTgift(c.tgift +sb);
					c.setHappiness(c.intel);
					break;
			}
			
			switch(c.gtype){
				case "choosy":
					sg = sb + (2*lv) - c.maintenence;
					c.setHappiness(c.happiness + (int)Math.log(sg));
					break;
				case "normal":
					sg = sb + v - c.maintenence;
					
					c.setHappiness(c.happiness + sg);
					break;
				case "desperate" :
					sg = sb - c.maintenence;
					int h = (int)Math.exp(sg);
					c.setHappiness(c.happiness + h);
					break;
			}
			if(c.btype == "generous"){
				c.setHappiness(2*c.happiness);
			}
		}
		Collections.sort(clist, new Couplesort());
		int i;
		System.out.println("15 most happiest couple");
		for(i=0;i<15;i++){
			System.out.println(clist.get(i).gname+"\t"+clist.get(i).bname);
		}
//		for(Couple c: clist){
//			System.out.println(c.bname +"\t"+c.gname);
//		}
	}
	
	
}

class Gifsort implements Comparator<Gift>{
		public int compare(Gift g1, Gift g2){
			if(g1.price > g2.price){
				return 1;
			}
			else{
				return -1;
			}
		}
}

class Couplesort implements Comparator<Couple>{
		public int compare(Couple c1, Couple c2){
			if(c1.happiness > c2.happiness){
				return 1;
			}
			else{
				return -1;
			}
		}
}