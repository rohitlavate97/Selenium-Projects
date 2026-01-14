package base_concepts;

public class ReverseNumber {

	public static void main(String[] args) {
		int number = 123;
		int temp;
		int reverse= 0;
		while(number>0) {
			temp = number%10;
			reverse = reverse*10 + temp;
			number = number/10;
			}
		System.out.println(reverse);
	}

}
