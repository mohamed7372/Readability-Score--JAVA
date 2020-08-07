package readabilityScore;

import java.util.Scanner;

public class Main {

	static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String input = sc.nextLine();
		String[] words = input.split("\\s");
		int nbr = 0, som = 0, nbrPh = 0;
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].matches(".+[.!?]$") || i == words.length - 1){
				nbr++;
				som += nbr;
				nbrPh++;
				nbr = 0;
			}
			else 
				nbr++;
		}
		float moy = (float)som / (float)nbrPh;
		
		if (moy <= 10) 
			System.out.println("EASY");
		else
			System.out.println("HARD");
	}
}
