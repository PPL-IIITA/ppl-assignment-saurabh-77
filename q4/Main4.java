import java.util.*;
import java.io.*;

public class Main4{
	public static void main(String args[]) throws Exception{
		
//		Generator gen = new Generator();
//		gen.boy_generator();
//		gen.girl_generator();
//		gen.gift_generator();
		//read boys data from file and store it to blist arraylist;
		BufferedReader br = null;
        String line = "";
        ArrayList<Boy> blist = new ArrayList<Boy>();
		try {
			String loc = "boy.csv";
            br = new BufferedReader(new FileReader(loc));
            while ((line = br.readLine()) != null) {
				
                
                String[] by = line.split(",");
				Boy b = new Boy(by[0], Integer.parseInt(by[1]), Integer.parseInt(by[2]), by[4], (float)Double.parseDouble(by[3]));
				b.setStatus("single");
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
			String locg = "girl.csv";
            br = new BufferedReader(new FileReader(locg));
            while ((line = br.readLine()) != null) {
				
                String[] by = line.split(",");
				char c[] = by[4].toCharArray();
				Girl g = new Girl(by[0], Integer.parseInt(by[1]), Integer.parseInt(by[2]), by[5], (float)Double.parseDouble(by[3]), c[0]);
				g.setStatus("single");
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
							if(b.intelligence >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = b.intelligence;
								by.setStatus("committed");
								g.setStatus("committed");
							}
						}
						break;
					case 'A':
						for(Boy b : blist){
							if(b.attractiveness >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = b.attractiveness;
								by.setStatus("committed");
								g.setStatus("committed");
							}
						}
						break;
					case 'R':
						for(Boy b : blist){
							if((int)b.budget >= max && b.budget >= g.maintenence && b.status.equals("single")){
								by = b;
								max = (int)b.budget;
								by.setStatus("committed");
								g.setStatus("committed");
							}
						}
						break;
						
						
						
			}
			
			if(by!=null){
				
//				System.out.printf("%s\t%s\n", g.name, by.name);
				Couple c = new Couple(g.name, by.name, (int)by.budget, (int)g.maintenence, by.type, g.type, g.intelligence);
				clist.add(c);
			}
		}
//		for(Couple c : clist)
//			System.out.println(c.bname +"  " +c.gname);
	
		ArrayList<Gift> gif_list = new ArrayList<Gift>();
		try {
			String locgif = "gift.csv";
            br = new BufferedReader(new FileReader(locgif));
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
		int count = 0;
		for(Couple c : clist){
				count=count+1;
		}
		System.out.println("Removing 7 least happiest couple");
	//	System.out.println(count);
		int i;
		for(i=count-1;i>=(count-7);i--){
//			remove(clist.get(i));
			String gn = clist.get(i).gname;
			String bn = clist.get(i).bname;
			for(Boy b : blist){
					if(clist.get(i).bname.equals(b.name)){
						b.setStatus("single");
						break;
					}
			}
			for(Girl g : glist){
					if(clist.get(i).gname.equals(g.name)){
						g.setStatus("single");	
						break;
					}
			}
			for(Girl g : glist){
				if(g.status.equals("single")){
					
					char ch =  g.boytype;
					Boy by = null;
					int max=0;
					switch(ch){
							
							case 'I':
								for(Boy b : blist){
									if(b.intelligence >= max && b.budget >= g.maintenence && b.status.equals("single")){
										if(g.name.compareTo(gn)!=0 && b.name.compareTo(bn)!=0){
											by = b;
											max = b.intelligence;
											by.setStatus("committed");
											g.setStatus("committed");
										}
									}
								}
								break;
							case 'A':
								for(Boy b : blist){
									if(b.attractiveness >= max && b.budget >= g.maintenence && b.status.equals("single")){
										if(g.name.compareTo(gn)!=0 && b.name.compareTo(bn)!=0){
											by = b;
											max = b.attractiveness;
											by.setStatus("committed");
											g.setStatus("committed");
										}
									}
								}
								break;
							case 'R':
								for(Boy b : blist){
									if((int)b.budget >= max && b.budget >= g.maintenence && b.status.equals("single")){
										if(g.name.compareTo(gn)!=0 && b.name.compareTo(bn)!=0){
											by = b;
											max = (int)b.budget;
											by.setStatus("committed");
											g.setStatus("committed");
										}
									}
								}
								break;	
					}
				
					if(by!=null){
		//				System.out.printf("%s\t%s\n", g.name, by.name);
						Couple c = new Couple(g.name, by.name, (int)by.budget, (int)g.maintenence, by.type, g.type, g.intelligence);
						clist.add(c);
					}
				}
			}
			clist.remove(clist.get(i));
		}
		System.out.println("Newly formed couples are: ");
		for(i=(count-7);i<clist.size();i++){
			System.out.println(clist.get(i).gname+"\t"+clist.get(i).bname);
		}
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