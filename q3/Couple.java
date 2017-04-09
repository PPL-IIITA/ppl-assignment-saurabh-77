import java.util.*;
import java.io.*;

public class Couple{
	String bname, gname, btype, gtype;
	int budget, maintenence, happiness, intel;
	int tgift;
	
	Couple(String gn, String bn, int b, int m, String bt, String gt, int intellegence){
		bname = bn;
		gname = gn;
		budget = b;
		maintenence = m;
		btype = bt;
		gtype = gt;
		intel = intellegence;
	}
	public void setHappiness(int x){
		happiness = x;
	}
	//public void setTgift(int s){
//		tgift = s;
//	}	
}