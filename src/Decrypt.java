import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Decrypt {

	static String ciphertext;
	static String plaintext;
	static String input;
	static Scanner scanner;
	
	public static void main(String[] args){
		
		scanner = new Scanner(System.in);
		
		try{
			ciphertext = readFile("text.txt", Charset.defaultCharset());
		} catch(IOException e){
			System.out.println("could not find file");
		}
		
		plaintext = ciphertext;
		
		// character count
		System.out.println("Character count: " + countCharacters(ciphertext));
		
		// letter frequency count
		int freq[] = letterFreq(ciphertext);
		
		// print out letter frequencies
		for(int i=0; i<26; i++){
			char c = (char) (i + 97);
			System.out.println(c + ": " + freq[i]);
		}
		
		System.out.println("\n" + plaintext + "\n");
		
		while(true){
			System.out.print("replace: ");
			String oldLetter = scanner.nextLine();
			if(oldLetter.equals("quit")){
				break;
			} else if(oldLetter.equals("reset")){
				plaintext = ciphertext;
				System.out.println("\n" + plaintext + "\n");
				continue;
			}
			System.out.print("with: ");
			String newLetter = scanner.nextLine();
			oldLetter = oldLetter.toUpperCase();
			newLetter = newLetter.toUpperCase();
			plaintext = replace(oldLetter.charAt(0), newLetter.charAt(0), plaintext);
			System.out.println(plaintext);
			System.out.println();
		}
		
	}//end main method

	private static int countCharacters(String text){
		int count = 0;
		for(int i=0; i<text.length(); i++){
			if(Character.isLetter(text.charAt(i))){
				count++;
			}
		}
		return count;
	}
	
	private static int[] letterFreq(String text){
		
		//count of a particular letter
		int[] count = new int[26];
		
		for(int i=0; i<text.length(); i++){
			if(!Character.isLetter(text.charAt(i))){
				continue;
			}
			char c = text.charAt(i);
			c = Character.toUpperCase(c);
			count[(int) c - 65]++;
		}
		
		return count;
		
	}// end letterFreq method
	
	private static String replace(char oldLetter, char newLetter, String plaintext){
		
		char[] arr = plaintext.toCharArray();
		
		for(int i=0; i<arr.length; i++){
			if(arr[i] == oldLetter){
				arr[i] = newLetter;
			}
		}
		
		return new String(arr);
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
}