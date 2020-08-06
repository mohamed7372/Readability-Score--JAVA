package readabilityScore;

import java.util.Scanner;

public class Main {

	static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String input = sc.nextLine();
		if (input.length() > 100) 
			System.out.println("HARD");
		else
			System.out.println("EASY");
	}

}
