package base_concepts;

import java.util.Scanner;

public class ArmStrongNum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();
		int age1 = age;
		int count = 0;
		while(age1>0) {
			age1 = age1/10;
			count++;
		}
		System.out.println(count);
		int temp;
		int sum = 0;
		while(age>0) {
			temp = age%10;
			sum = sum + (int)Math.pow(temp, count);
			age = age/10;
		}
		System.out.println(sum);
	}

}
