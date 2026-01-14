package base_concepts;

import java.util.Scanner;

public class SwitchCase {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();
		
		switch(age) {
		case 17 : System.out.println("You are not eligible for voting");
		         break;
		         
		case 18: System.out.println("You are just eligible from this year");
		         break;
		         
		case 19: System.out.println("Eligible, enjoy your life with democracy");
		         break;
		        
		default: System.out.println("why are you even trying to vote,just chill....");
		}
		
	}

}
