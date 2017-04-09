import java.util.*;
import java.io.*;

public class Generator{
	
	
//	public void girl_generator() throws FileNotFoundException{
	public void girl_generator() throws FileNotFoundException{
		int i, intel;
//		ArrayList<Girl> glist = new ArrayList<Girl>();
		String name;
		int attractiveness;
		float budget;
		char choice;
		String str;
		String[] type = {"choosy", "normal", "desperate"};
		PrintWriter pw = new PrintWriter(new File("girl.csv"));
        StringBuilder sb = new StringBuilder();
		Random r = new Random();
		
		for(i=1;i<=50;i++){
//			Girl g = new Girl();
			char[] ch = {'I', 'A', 'R'};//girls can choose boys : I(intellegence), A(attractiveness), R(richness)
			name = "Girl"+i;
//			g.setName(name);
			attractiveness = r.nextInt(100)+1;
//			g.setAttractiveness(attractiveness);
			intel = r.nextInt(10)+1;
//			g.setIntellegence(intel);
			budget = (float)r.nextInt(6500)+500;
//			g.setMainentence(budget);
			choice = ch[r.nextInt(3)];
			str = type[r.nextInt(3)];
//			g.setBoytype(choice);
//			glist.add(g);
			
			
			sb.append(name);
			sb.append(',');
			
			sb.append(attractiveness);
			sb.append(',');
			sb.append(intel);
			sb.append(',');
			sb.append(budget);
			sb.append(',');
			sb.append(choice);
			sb.append(',');
			sb.append(str);
			sb.append('\n');
			
        
		}
		pw.write(sb.toString());
		pw.close();
//		return glist;
	}
	
	public void boy_generator() throws FileNotFoundException{
			int i, intel;
//		ArrayList<Boy> blist = new ArrayList<Boy>();
		String name;
		int attractiveness;
		float budget;
		String[] type = {"miser", "generous", "geeks"};
		String str;
		//char choice;
		PrintWriter pw = new PrintWriter(new File("boy.csv"));
        StringBuilder sb = new StringBuilder();
		Random r = new Random();
		
		for(i=1;i<=150;i++){
//			Boy b = new Boy();
//			char[] ch = {'I', 'A', 'R'};
			name = "Boy"+i;
			attractiveness = r.nextInt(100)+1;
			intel = r.nextInt(10)+1;
			budget = (float)r.nextInt(9000)+1000;
			str = type[r.nextInt(3)];
//			b.setName(name);		
//			b.setAttractiveness(attractiveness);		
//			b.setIntellegence(intel);	
//			b.setBudget(budget);
//			b.setStatus("single");
			//choice = ch[r.nextInt(3)];
			//g.setsetBoytype(choice);
//			blist.add(b);
			
			
			sb.append(name);
			sb.append(',');
			sb.append(attractiveness);
			sb.append(',');
			sb.append(intel);
			sb.append(',');
			sb.append(budget);
			sb.append(',');
			sb.append(str);
			
			//sb.append(',');
			//sb.append(choice);
			sb.append('\n');
			
        
		}
		pw.write(sb.toString());
		pw.close();
//		return blist;
	}
	
	public void gift_generator() throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(new File("gift.csv"));
        StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int i;
		String str[] = {"essantial", "luxury", "utility"};
		for(i = 1; i<=500; i++){
			sb.append("gift_"+i);
			sb.append(',');
			sb.append(r.nextInt(2000)+300);
			sb.append(',');
			sb.append(r.nextInt(100)+50);
			sb.append(','); 
			sb.append(r.nextInt(5)+2);
			sb.append(',');
			sb.append(str[r.nextInt(3)]);
			
			//sb.append(',');
			//sb.append(choice);
			sb.append('\n');
		}
	
		pw.write(sb.toString());
		pw.close();;
	}
	public static void main(String args[]) throws Exception{
		Generator gen = new Generator();
		gen.boy_generator();
		gen.girl_generator();
		gen.gift_generator();
	}
	
}