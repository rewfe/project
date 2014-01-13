package my.sample.i18n;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UtfTools {
	static String[][] ru = new String[][]{
		{"\u0410", "\\\\u0410"}, //А
		{"\u0411", "\\\\u0411"}, //Б
		{"\u0412", "\\\\u0412"}, //В
		{"\u0413", "\\\\u0413"}, //Г
		{"\u0414", "\\\\u0414"}, //Д
		{"\u0415", "\\\\u0415"}, //Е
		{"\u0416", "\\\\u0416"}, //Ж
		{"\u0417", "\\\\u0417"}, //З
		{"\u0418", "\\\\u0418"}, //И
		{"\u0419", "\\\\u0419"}, //Й
		{"\u041a", "\\\\u041a"}, //К
		{"\u041b", "\\\\u041b"}, //Л
		{"\u041c", "\\\\u041c"}, //М
		{"\u041d", "\\\\u041d"}, //Н
		{"\u041e", "\\\\u041e"}, //О
		{"\u041f", "\\\\u041f"}, //П
		{"\u0420", "\\\\u0420"}, //Р
		{"\u0421", "\\\\u0421"}, //С
		{"\u0422", "\\\\u0422"}, //Т
		{"\u0423", "\\\\u0423"}, //У
		{"\u0424", "\\\\u0424"}, //Ф
		{"\u0425", "\\\\u0425"}, //Х
		{"\u0426", "\\\\u0426"}, //Ц
		{"\u0427", "\\\\u0427"}, //Ч
		{"\u0428", "\\\\u0428"}, //Ш
		{"\u0429", "\\\\u0429"}, //Щ
		{"\u042a", "\\\\u042a"}, //Ъ
		{"\u042b", "\\\\u042b"}, //Ы
		{"\u042c", "\\\\u042c"}, //Ь
		{"\u042d", "\\\\u042d"}, //Э
		{"\u042e", "\\\\u042e"}, //Ю
		{"\u042f", "\\\\u042f"}, //Я
		{"\u0430", "\\\\u0430"}, //а
		{"\u0431", "\\\\u0431"}, //б
		{"\u0432", "\\\\u0432"}, //в
		{"\u0433", "\\\\u0433"}, //г
		{"\u0434", "\\\\u0434"}, //д
		{"\u0435", "\\\\u0435"}, //е
		{"\u0436", "\\\\u0436"}, //ж
		{"\u0437", "\\\\u0437"}, //з
		{"\u0438", "\\\\u0438"}, //и
		{"\u0439", "\\\\u0439"}, //й
		{"\u043a", "\\\\u043a"}, //к
		{"\u043b", "\\\\u043b"}, //л
		{"\u043c", "\\\\u043c"}, //м
		{"\u043d", "\\\\u043d"}, //н
		{"\u043e", "\\\\u043e"}, //о
		{"\u043f", "\\\\u043f"}, //п
		{"\u0440", "\\\\u0440"}, //р
		{"\u0441", "\\\\u0441"}, //с
		{"\u0442", "\\\\u0442"}, //т
		{"\u0443", "\\\\u0443"}, //у
		{"\u0444", "\\\\u0444"}, //ф
		{"\u0445", "\\\\u0445"}, //х
		{"\u0446", "\\\\u0446"}, //ц
		{"\u0447", "\\\\u0447"}, //ч
		{"\u0448", "\\\\u0448"}, //ш
		{"\u0449", "\\\\u0449"}, //щ
		{"\u044a", "\\\\u044a"}, //ъ
		{"\u044b", "\\\\u044b"}, //ы
		{"\u044c", "\\\\u044c"}, //ь
		{"\u044d", "\\\\u044d"}, //э
		{"\u044e", "\\\\u044e"}, //ю
		{"\u044f", "\\\\u044f"}, //я
		{"\u0451", "\\\\u0451"}  //ё
	};
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader(new File("src/my/sample/i18n/hay_utf8.properties"));
		BufferedReader reader = new BufferedReader(fr);
		PrintWriter writer = new PrintWriter(new File("src/my/sample/i18n/hay.properties"));
		
		String line = null;
		while ((line=reader.readLine())!=null) {
			line=new String(line.getBytes(fr.getEncoding()));
			for (int i = 0; i < ru.length; i++) {
				line=line.replaceAll("("+ru[i][0]+")", ru[i][1]);
			}
			System.out.println(line);
			writer.println(line);
		}
		writer.flush();
		writer.close();

	}
}
