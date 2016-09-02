
public class Tree_Bakan {
	public static void main(String[] args) throws InterruptedException {
		TreeDisplay morseDisplay=new TreeDisplay();
		TreeDisplay brailleDisplay=new TreeDisplay();

		//Create Morse Decoding tree
		TreeNode morseTree=TreeUtilities.createMorseDecodingTree(morseDisplay);
		morseDisplay.displayTree(morseTree);
		System.out.println(TreeUtilities.decode(morseTree, "-- --- .-. ... . -.-. --- -.. .", morseDisplay, false));

		System.out.println();

		//Create Braille Decoding tree
		TreeNode brailleTree=TreeUtilities.createBrailleDecodingTree(brailleDisplay);
		brailleDisplay.displayTree(brailleTree);
		System.out.println(TreeUtilities.decode(brailleTree, ".-. .-... . -.. .-.-. .-.-. .--. .. .--.. ..-. .--.", brailleDisplay, true));
	}
}
