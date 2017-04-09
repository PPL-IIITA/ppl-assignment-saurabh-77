
import java.util.*;
import java.io.*;

public class Boy extends Person{
	float budget;
	String status;
	Boy(String name, int attractiveness, int intelligence, String type, float budget){
		super(name, attractiveness, intelligence, type);
		this.budget = budget;
//		this.status = status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}