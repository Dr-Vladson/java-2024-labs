package lab4;

/**
 * The Lab4 class demonstrates text processing using custom classes for managing letters, words, sentences, and text.
 * It processes a given text by removing all occurrences of the first character of each word after its first occurrence.
 */
public class Lab4 {

    /**
     * The main method of the Lab4 class. It initializes the text processing, demonstrates the functionality,
     * and prints the original and processed text.
     *
     * @param args Command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        try {
            System.out.println("Лаба4 Любашенко Владислав IС-22 Варiант 14");
            int studentId = 14;
            int C3 = studentId % 3;
            int C17 = studentId % 17;
            System.out.println("C3 = " + C3 + "; C17 = " + C17);

            // Creating a sample input text
            StringBuffer inputBuffer = new StringBuffer("appalea ApAle Aaaa yuiiiyyyyyyyy нрно антанараву ВаВВВВВц! Scarlett O'Hara was not beautiful, but men seldom realized it. In the second growth of the cotton fields, she was as well known as any of the other young women in the neighborhood. She had dark eyes, a dimple in her chin, and an almost imperceptible twist to her smile that suggested mischief. She was dressed in the fashionable style of the day, and had an elegance that charmed many around her. Scarlett's father, Gerald O'Hara, was a wealthy plantation owner, and Scarlett was accustomed to getting what she wanted. As the youngest of three daughters, she was spoiled and used to getting attention from everyone around her. But at the moment, her thoughts were preoccupied with Ashley Wilkes, the young man who was the object of her affections. Scarlett, although in love with him, was unaware of the complexities of love and life that would soon unfold. The winds of change were blowing over the land of Georgia, and the world Scarlett knew was about to be torn apart.");

            System.out.println("Original Text: " + inputBuffer.toString());

            // Create a Text object to handle text processing
            Text text = new Text(inputBuffer);
            text.handleText(); // Process the text
            System.out.println("\nProcessed Text: " + text.toString());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/**
 * The Text class represents a collection of sentences.
 * It provides methods to handle and process the text by parsing it into sentences and applying specific transformations.
 */
class Text {
    private Sentence[] sentences;

    /**
     * Constructs a Text object and parses the input text into sentences.
     *
     * @param textBuffer The text input to be parsed into sentences
     */
    public Text(StringBuffer textBuffer) {
        parseText(textBuffer);
    }

    /**
     * Parses the input text into individual sentences.
     * The sentences are split by punctuation marks like period, exclamation mark, or question mark.
     *
     * @param textBuffer The text input to be parsed
     */
    private void parseText(StringBuffer textBuffer) {
        String[] sentenceStrings = textBuffer.toString().split("(?<=\\.|!|\\?)\\s*");
        sentences = new Sentence[sentenceStrings.length];
        for (int i = 0; i < sentenceStrings.length; i++) {
            sentences[i] = new Sentence(sentenceStrings[i]);
        }
    }

    /**
     * Processes the text by handling each sentence.
     */
    public void handleText() {
        for (Sentence sentence : sentences) {
            sentence.handleSentence();
        }
    }

    /**
     * Returns the processed text as a string.
     *
     * @return The processed text
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Sentence sentence : sentences) {
            result.append(sentence.toString()).append(" ");
        }
        return result.toString().trim();
    }
}

/**
 * The Sentence class represents a collection of words in a sentence.
 * It provides methods for handling each word within a sentence.
 */
class Sentence {
    private Word[] words;

    /**
     * Constructs a Sentence object by splitting a sentence string into words.
     *
     * @param sentence The sentence string to be split into words
     */
    public Sentence(String sentence) {
        String[] wordStrings = sentence.split(" ");
        words = new Word[wordStrings.length];
        for (int i = 0; i < wordStrings.length; i++) {
            words[i] = new Word(wordStrings[i]);
        }
    }

    /**
     * Processes each word in the sentence.
     */
    public void handleSentence() {
        for (Word word : words) {
            word.handleWord();
        }
    }

    /**
     * Returns the processed sentence as a string.
     *
     * @return The processed sentence
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Word word : words) {
            result.append(word.toString()).append(" ");
        }
        return result.toString().trim();
    }
}

/**
 * The Word class represents a word consisting of a sequence of letters.
 * It provides methods for handling the transformation of the word by removing subsequent occurrences of its first character.
 */
class Word {
    private Letter[] letters;

    /**
     * Constructs a Word object from a word string and converts it into an array of Letter objects.
     *
     * @param word The word string to be converted into Letter objects
     */
    public Word(String word) {
        letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
    }

    /**
     * Handles the word by removing all occurrences of its first character after its first occurrence.
     */
    public void handleWord() {
        StringBuilder handledWord = new StringBuilder();
        char firstChar = letters[0].getCharacter();
        handledWord.append(firstChar);

        for (int i = 1; i < letters.length; i++) {
            if (letters[i].getCharacter() != firstChar) {
                handledWord.append(letters[i].getCharacter());
            }
        }

        // Update the letters array with the handled word
        letters = new Letter[handledWord.length()];
        for (int i = 0; i < handledWord.length(); i++) {
            letters[i] = new Letter(handledWord.charAt(i));
        }
    }

    /**
     * Returns the processed word as a string.
     *
     * @return The processed word
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Letter letter : letters) {
            result.append(letter.toString());
        }
        return result.toString();
    }
}

/**
 * The Letter class represents a single character in a word.
 * It provides methods for retrieving the character and converting it to a string.
 */
class Letter {
    private char character;

    /**
     * Constructs a Letter object with the specified character.
     *
     * @param character The character to be represented by this Letter object
     */
    public Letter(char character) {
        this.character = character;
    }

    /**
     * Returns the character of this Letter.
     *
     * @return The character of this Letter
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Returns the character as a string.
     *
     * @return The character as a string
     */
    @Override
    public String toString() {
        return String.valueOf(character);
    }
}