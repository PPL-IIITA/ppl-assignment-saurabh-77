
import java.util.*;
import java.io.*;

public class Girl extends Person{
	float maintenence;
	char boytype;
	String status;
	
	Girl(String name, int attractiveness, int intelligence, String type, float maintenence, char boytype){
		super(name, attractiveness, intelligence, type);
		this.maintenence= maintenence;
		this.boytype = boytype;
	}
	public void setStatus(String s){
		status = s;
	}
}