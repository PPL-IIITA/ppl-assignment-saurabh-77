import java.util.*;
import java.io.*;

public class Main5{
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
			
            br = new BufferedReader(new FileReader("boy.csv"));
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
			
            br = new BufferedReader(new FileReader("girl.csv"));
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
		Collections.sort(blist, new Boysort());
		Collections.sort(glist, new Girlsort());
		
		ArrayList<Couple> clist = new ArrayList<Couple>(); 
		int i=0, x, y;
		do{
			x=0;
			y=0;
			if(i%2==0){
				for(Girl g : glist){
					char ch =  g.boytype;
					Boy by = null;
					int max=0;
					if(g.status.equals("single")){
						
						switch(ch){
								case 'I':
									for(Boy b : blist){
										if(b.intelligence >= max && b.budget >= g.maintenence && b.status.equals("single")){
											by = b;
											max = b.intelligence;
											
										}
									}
									break;
								case 'A':
									for(Boy b : blist){
										if(b.attractiveness >= max && b.budget >= g.maintenence && b.status.equals("single")){
											by = b;
											max = b.attractiveness;
											
										}
									}
									break;
								case 'R':
									for(Boy b : blist){
										if((int)b.budget >= max && b.budget >= g.maintenence && b.status.equals("single")){
											by = b;
											max = (int)b.budget;
											
										}
									}
									break;
									
									
									
						}
					}
					
					if(by!=null){
		//				System.out.printf("%s\t%s\n", g.name, by.name);
						by.setStatus("committed");
						g.setStatus("committed");
						i++;
						y=1;
						Couple c = new Couple(g.name, by.name, (int)by.budget, (int)g.maintenence, by.type, g.type, g.intelligence);
						clist.add(c);
						break;
					}
				}
			
			}
			if(i%2!=0){
				for(Boy b : blist){
						Girl gy = null;
						int max = 0;
						if(b.status.equals("single")){
							for(Girl g : glist){
									if(g.attractiveness > max && b.status.equals("single")){
										gy = g;
										max = g.attractiveness;
										
									}
							}
						}
						if(gy!=null){
			//				System.out.printf("%s\t%s\n", g.name, by.name);
							b.setStatus("committed");
							gy.setStatus("committed");
							i++;
							x=1;
							Couple c = new Couple(gy.name, b.name, (int)b.budget, (int)gy.maintenence, b.type, gy.type, b.intelligence);
							clist.add(c);
							break;
						}
				}
			}
			
		}while(x==1 && y==1);
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
//System.out.println(clist.size());
		for(Couple c : clist){
			int sg = 0;
			int sb = 0, v = 0, lv = 0;
			i=0;
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

class Boysort implements Comparator<Boy>{
		public int compare(Boy b1, Boy b2){
			if(b1.attractiveness > b2.attractiveness){
				return 1;
			}
			else{
				return -1;
			}
		}
}

class Girlsort implements Comparator<Girl>{
		public int compare(Girl g1, Girl g2){
			if(g1.maintenence > g2.maintenence){
				return 1;
			}
			else{
				return -1;
			}
		}
}