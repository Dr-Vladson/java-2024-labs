package lab2;

public class Lab2 {
    public static void main(String[] args) {
        try {
            System.out.println("Лаба2 Любашенко Владислав IС-22 Варiант 14");
            int studentId = 14;
            int C3 = studentId % 3;
            int C17 = studentId % 17;
            System.out.println("C3 = " + C3 + "; C17 = " + C17);

            StringBuffer inputBuffer = new StringBuffer("appalea ApAle Aaaa yuiiiyyyyyyyy нрно антанараву ВаВВВВВц");
            System.out.println("Оригiнальний текст: " + inputBuffer.toString());
            StringBuffer resultBuffer = handleText(new StringBuffer(inputBuffer));
            System.out.println("Оброблений текст: " + resultBuffer.toString());
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    public static StringBuffer handleText(StringBuffer text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }

        StringBuffer resultText = new StringBuffer();
        StringBuffer wordBuffer = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == ' ') {
                resultText.append(handleWord(wordBuffer));
                resultText.append(" ");
                wordBuffer.setLength(0);
            } else {
                wordBuffer.append(currentChar);
            }
        }

        return resultText;
    }

    public static StringBuffer handleWord(StringBuffer word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }

        StringBuffer handledWord = new StringBuffer();
        char firstChar = word.charAt(0);
        handledWord.append(firstChar);
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) != firstChar) {
                handledWord.append(word.charAt(i));
            }
        }

        return handledWord;
    }
}



