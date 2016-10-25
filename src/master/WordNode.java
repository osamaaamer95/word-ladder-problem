package master;

public class WordNode {

	private String word;
	private WordNode nextWord;
	
	public WordNode() {
		// TODO Auto-generated constructor stub
		nextWord = null;
	}

	public WordNode(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public WordNode getNextWord() {
		return nextWord;
	}

	public void setNextWord(WordNode nextWord) {
		this.nextWord = nextWord;
	}

}
