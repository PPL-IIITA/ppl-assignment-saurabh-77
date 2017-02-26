import java.util.*;
import java.io.*;

public class Gift{
	int price;
	String name;
	int value;
	String type;
	int left;
	//int value;
	//int lvalue;
	Gift(String name, int price, int value, int left, String type){
		this.name = name;
		this.price = price;
		this.value = value;
		this.left = left;
		this.type = type;
		
	}
	public void setLeft(int l){
			left = l;
	}
	/*void setValue(int n){
		value = n;
	}
	void setLvalue(int n){
		lvalue = n;
	}*/
}

