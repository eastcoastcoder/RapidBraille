/*
 * Original Author: Eric Bakan
 * Source: https://github.com/ebakan/Data-Structures/blob/master/TreeLab/src/TreeUtilities.java
 * Additional unused methods have been omitted.
 *
 * Braille Addition: Ethan Richardson
 * Taking a hint from the original author I saw drastic similarities
 * between Morse Code and Braille. In a morse code tree, the paths
 * are as simple as: go left if dot, go right if dash.
 * I set up a tree for braille that took me four different routes
 * L: Append a dot
 * LM: Append 1x dash, one dot
 * RM: Append 2x dashes, one dot
 * R:  Append 3x dashes, one dot
 *
 * Seeing this 4-ary tree I figured something could be optimized,
 * I painstakingly preformed a Knuth transformation by hand to come
 * up with a much different logical structure.
 * L: Append a dot
 * R: Remove last dot, append last+1 dot
 *
 * Removal of characters in code proved to be difficult, so instead
 * I found a pattern of formatting the braille code to fit the criteria
 * of morse code. Essentially, by taking the start, middle, and ending
 * points of a braille input I could follow the original left dot,
 * right dot logic. It must be formatted: start, end, middle.
 *
 * This worked for all but the characters that begin on a dash.
 * The logic for dashed characters was to prepend a dot, and remove
 * the last character completely.
 *
 * Areas of Concern: After the Knuth X-form, the letter V
 * became a right child of R. It is instead supposed to be
 * L's left child. This somehow worked its way out after
 * implementation.
 */
//A container for useful static methods that operate on TreeNode objects.
public class TreeUtilities {
	//Flag for resetting braille character shuffle
	static boolean fixed = false;

	//precondition:  t is non-empty
	//postcondition: returns the value in the leftmost node of t.
	public static Object leftmost(TreeNode t) {
		while (t.getLeft() != null)
			t = t.getLeft();
		return t.getValue();
	}

	//precondition:  t is non-empty
	//postcondition: returns the value in the rightmost node of t.
	public static Object rightmost(TreeNode t) {
		if (t.getRight() == null)
			return t.getValue();
		return rightmost(t.getRight());
	}

	//postcondition: returns the maximum depth of t, where an empty tree
	//               has depth 0, a tree with one node has depth 1, etc
	public static int maxDepth(TreeNode t) {
		if (t == null)
			return 0;
		int leftDepth = maxDepth(t.getLeft());
		int rightDepth = maxDepth(t.getRight());
		return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
	}

	//postcondition:  returns a tree for decoding Morse code
	public static TreeNode createMorseDecodingTree(TreeDisplay display) {
		TreeNode tree = new TreeNode("Morse Tree");
		display.displayTree(tree);
		insert(tree, "a", ".-", display, false);
		insert(tree, "b", "-...", display, false);
		insert(tree, "c", "-.-.", display, false);
		insert(tree, "d", "-..", display, false);
		insert(tree, "e", ".", display, false);
		insert(tree, "f", "..-.", display, false);
		insert(tree, "g", "--.", display, false);
		insert(tree, "h", "....", display, false);
		insert(tree, "i", "", display, false);
		insert(tree, "j", ".---", display, false);
		insert(tree, "k", "-.-", display, false);
		insert(tree, "l", ".-..", display, false);
		insert(tree, "m", "--", display, false);
		insert(tree, "n", "-.", display, false);
		insert(tree, "o", "---", display, false);
		insert(tree, "p", ".--.", display, false);
		insert(tree, "q", "--.-", display, false);
		insert(tree, "r", ".-.", display, false);
		insert(tree, "s", "...", display, false);
		insert(tree, "t", "-", display, false);
		insert(tree, "u", "..-", display, false);
		insert(tree, "v", "...-", display, false);
		insert(tree, "w", ".--", display, false);
		insert(tree, "x", "-..-", display, false);
		insert(tree, "y", "-.--", display, false);
		insert(tree, "z", "--..", display, false);
		System.out.println("DONE!");
		return tree;
	}

	//postcondition:  returns a tree for decoding Braille code
	public static TreeNode createBrailleDecodingTree(TreeDisplay display) {
		TreeNode tree = new TreeNode("Braille Tree");
		display.displayTree(tree);
		insert(tree, "a", ".", display, true);
		insert(tree, "b", ".-.", display, true);
		insert(tree, "c", "..", display, true);
		insert(tree, "d", "..-.", display, true);
		insert(tree, "e", ".--.", display, true);
		insert(tree, "f", "...", display, true);
		insert(tree, "g", "....", display, true);
		insert(tree, "h", ".-..", display, true);
		insert(tree, "i", "-..", display, true);
		insert(tree, "j", "-...", display, true);
		insert(tree, "k", ".---.", display, true);
		insert(tree, "l", ".-.-.", display, true);
		insert(tree, "m", "..--.", display, true);
		insert(tree, "n", "..-..", display, true);
		insert(tree, "o", ".--..", display, true);
		insert(tree, "p", "...-.", display, true);
		insert(tree, "q", ".....", display, true);
		insert(tree, "r", ".-...", display, true);
		insert(tree, "s", "-..-.", display, true);
		insert(tree, "t", "-....", display, true);
		insert(tree, "u", ".---..", display, true);
		insert(tree, "v", ".-.-..", display, true);
		insert(tree, "w", "-...-.", display, true);
		insert(tree, "x", "..--..", display, true);
		insert(tree, "y", "..-...", display, true);
		insert(tree, "z", ".--...", display, true);
		System.out.println("DONE!");
		return tree;
	}

	//postcondition:  inserts the given letter into the decodingTree,
	//                in the appropriate position, as determined by
	//                the given Morse code sequence; lights up the display
	//                as it walks down the tree
	private static void insert(TreeNode decodingTree, String letter,
							   String code, TreeDisplay display, boolean isBraille) {
		display.visit(decodingTree);
		if (code.length() == 0) {
			decodingTree.setValue(letter);
			System.out.println("Placed " + letter);
			fixed = false;
		} else {
			//New Braille Logic
			if(isBraille && code.length() > 2 && !fixed)
				code = brailleHelper(code);

			char direction = code.charAt(0);

			if (direction == '.') {
				if (decodingTree.getLeft() == null)
					decodingTree.setLeft(new TreeNode("null"));
				insert(decodingTree.getLeft(), letter, code.substring(1), display, false);
			} else if (direction == '-') {
				if (decodingTree.getRight() == null)
					decodingTree.setRight(new TreeNode("null"));
				insert(decodingTree.getRight(), letter, code.substring(1), display, false);
			} else
				throw new IllegalStateException("STATE IMPOSSIBLE!");
		}
	}

	//precondition:  ciphertext is Morse code, consisting of dots, dashes, and spaces
	//postcondition: uses the given decodingTree to return the decoded message;
	//               lights up the display as it walks down the tree
	public static String decode(TreeNode decodingTree, String cipherText, TreeDisplay display, boolean isBraille) {
		display.visit(decodingTree);
		String[] letters = cipherText.split(" ");
		String out = "";
		for (String s : letters) {
			System.out.println("DECODING: " + s);
			out += decodeHelper(decodingTree, s, display, isBraille);
			System.out.println("DECODED: " + out);
		}
		return out;
	}

	//precondition:  cipherText is valid morse code for one character
	//postcondition: helps the decode program by using a binary
	//               tree to decode one character
	private static String decodeHelper(TreeNode decodingTree, String cipherText, TreeDisplay display, boolean isBraille) {
		display.visit(decodingTree);
		if (cipherText.length() == 0) {
			fixed = false; //Flag for resetting braille character shuffle
			return (String) decodingTree.getValue();
		}
		else {
			//New Braille Logic
			if(isBraille && cipherText.length() > 2 && !fixed)
				cipherText = brailleHelper(cipherText);

			char direction = cipherText.charAt(0);
			if (direction == '.')
				return decode(decodingTree.getLeft(), cipherText.substring(1), display, false);
			else if (direction == '-')
				return decode(decodingTree.getRight(), cipherText.substring(1), display, false);
			else
				throw new IllegalArgumentException("Invalid character: " + direction);
		}

	}

	private static String brailleHelper(String cipherText){
		fixed = true;
		char start = cipherText.charAt(0);
		char end = cipherText.charAt(cipherText.length()-1);
		String mid = cipherText.substring(1, cipherText.length() - 1);

		if(start == '.')
			return new StringBuilder().append(start).append(end).append(mid).toString();
		else
			return new StringBuilder().append('.').append(start).append(mid).toString();
	}
}