package readabilityScore;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String text = readFile("E:\\GitHub\\Readability-Score--JAVA\\src\\readabilityScore\\" + args[0]);
		
		System.out.println("The text is:");
		System.out.println(text);
		
		String[] words = allWords(text);
		int nbrWords = words.length;
		System.out.print("Words: " + nbrWords);
		
		int nbrSen = nbrSenctences(words);
		System.out.print("\nSentences: " + nbrSen);
		
		int nbrCha = nbrCharacters(text);
		System.out.print("\nCharacters: " + nbrCha);

		double score = 4.71 * ((double)nbrCha / nbrWords) + 0.5 * ((double)nbrWords / nbrSen)- 21.43;
		System.out.printf("\nThe score is: %.2f",score);
		
		System.out.printf("\nThis text should be understood by %s year olds.", catAge((int)Math.ceil(score)));
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
	static String catAge(int score) {
		switch (score) {
		case 1: 
			return "5-6";
		case 2: 
			return "6-7";
		case 3: 
			return "7-9";
		case 4: 
			return "9-10";
		case 5: 
			return "10-11";
		case 6: 
			return "11-12";
		case 7: 
			return "12-13";
		case 8: 
			return "13-14";
		case 9: 
			return "14-15";
		case 10: 
			return "15-16";
		case 11: 
			return "16-17";
		case 12: 
			return "17-18";
		case 13: 
			return "18-24";
		case 14: 
			return "24+";
		default:
			return null;
		}
	}
}
