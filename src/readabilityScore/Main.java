package readabilityScore;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String text = readFile("C:\\Users\\HP\\eclipse-work\\site jet brains\\src\\readabilityScore\\" + args[0]);
		
		System.out.println("The text is:");
		System.out.println(text);
		
		String[] words = allWords(text);
		int nbrWords = words.length;
		System.out.print("\nWords: " + nbrWords);
		
		int nbrSen = nbrSenctences(words);
		System.out.print("\nSentences: " + nbrSen);
		
		int nbrCha = nbrCharacters(text);
		System.out.print("\nCharacters: " + nbrCha);
		
		int nbrSly[] = nbrSylabels(words);
		System.out.print("\nSyllables: " + nbrSly[0]);
		
		System.out.print("\nPolysyllables: " + nbrSly[1]);
		
		System.out.print("\nEnter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
		String ch = sc.next();

		int age = 0, age1 = 0, age2 = 0, age3 = 0;
				
		switch (ch) {
		case "ARI": 
			double score = 4.71 * ((double)nbrCha / nbrWords) + 0.5 * ((double)nbrWords / nbrSen)- 21.43;
			age = catAge(score);
			System.out.printf("\nAutomated Readability Index: %.2f (about %d year olds).", score, age);			
			break;
		case "FK": 
			double score1 = 0.39 * ((double)nbrWords / nbrSen) + 11.8 * ((double)nbrSly[0] / nbrWords)- 15.59;
			age1 = catAge(score1);
			System.out.printf("\nFlesch–Kincaid readability tests: %.2f (about %d year olds).", score1, age1);
			break;
		case "SMOG": 
			double score2 = 1.043 * Math.sqrt(nbrSly[1] * (30.0 / nbrSen)) + 3.1291;
			age2 = catAge(score2);
			System.out.printf("\nSimple Measure of Gobbledygook: %.2f (about %d year olds).", score2, age2);
			break;
		case "CL": 
			double L = (double)nbrCha / nbrWords * 100.0;
			double S = (double)nbrSen / nbrWords * 100.0;
			double score3 = 0.0588 * L - 0.296 * S - 15.8;
			age3 = catAge(score3);
			System.out.printf("\nColeman–Liau index: %.2f (about %d year olds).", score3, age3);
			break;
		default :
			score = 4.71 * ((double)nbrCha / nbrWords) + 0.5 * ((double)nbrWords / nbrSen)- 21.43;
			age = catAge(score);
			System.out.printf("\nAutomated Readability Index: %.2f (about %d year olds).", score, age);		
			
			score1 = 0.39 * ((double)nbrWords / nbrSen) + 11.8 * ((double)nbrSly[0] / nbrWords)- 15.59;
			age1 = catAge(score1);
			System.out.printf("\nFlesch–Kincaid readability tests: %.2f (about %d year olds).", score1, age1);
			
			score2 = 1.043 * Math.sqrt(nbrSly[1] * (30.0 / nbrSen)) + 3.1291; 
			age2 = catAge(score2);
			System.out.printf("\nSimple Measure of Gobbledygook: %.2f (about %d year olds).", score2, age2);
			
			L = (double)nbrCha / nbrWords * 100.0;
			S = (double)nbrSen / nbrWords * 100.0;
			score3 = 0.0588 * L - 0.296 * S - 15.8;
			age3 = catAge(score3);
			System.out.printf("\nColeman–Liau index: %.2f (about %d year olds).", score3, age3);
		}
		
		double ageMoy = (age + age1 + age2 + age3) / 4.0;
		System.out.printf("\n\nThis text should be understood in average by %.2f year olds.", ageMoy);
	}
	
	static String readFile(String pathName) {
		try {
			return Files.readString(Paths.get(pathName));
		} 
		catch (Exception e) {
		}
		return null;
	}
	static String[] allWords(String input) {
		return input.split("\\s");
	}
	static int nbrSenctences(String[] words) {
		int nbrPh = 0;
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].matches(".+[.!?]$") || i == words.length - 1)
				nbrPh++;
		}
		
		return nbrPh;
	}
	static int nbrCharacters(String text) {
		int nb = 0;
		for (int i = 0; i < text.length(); i++) {
			String s = String.valueOf(text.charAt(i));
			if(s.matches("\\S")) 
				nb++;
		}
		return nb;
	}
	static int catAge(double score) {
		int scr = 0;
		if (score % 100 >= 50)
			scr = (int)Math.ceil(score);
		else
			scr = (int)Math.floor(score);
		
		switch (scr) {
		case 1: 
			return 6;
		case 2: 
			return 7;
		case 3: 
			return 9;
		case 4: 
			return 10;
		case 5: 
			return 11;
		case 6: 
			return 12;
		case 7: 
			return 13;
		case 8: 
			return 14;
		case 9: 
			return 15;
		case 10: 
			return 16;
		case 11: 
			return 17;
		case 12: 
			return 18;
		case 13: 
			return 24;
		default:
			return 0;
		}
	}
	static int[] nbrSylabels(String[] words) {
		int nbr = 0;
		int nbrP = 0;
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			int c = 0;
			// count all vowel
			for (int j = 0; j < s.length(); j++) {
				if (s.substring(j, j + 1).matches("[aeiouyAEIOUY]"))
					c++;
			}
			// count 1 when find two sylables together
			for (int j = 0; j < s.length() - 1; j++) {
				if (s.substring(j, j + 2).matches("[aeiouyAEIOUY][aeiouyAEIOUY]"))
					c--;
			}
			// if the last letter is 'e' does not count
			if(s.matches(".+e[?!,.]?"))
				c--;
			// if zero vowel that is mean one sylabels
			if (c == 0)
				c++;
			// som sylabels
			nbr += c;
			
			// polysylabels
			if (c >= 3)
				nbrP++;
		}
		return new int[] {nbr, nbrP};
	}
}
