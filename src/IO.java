import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {

	public static void main(String[] args) {
		
		try 
		{			
			String path = "src/data.txt";
			String outputFilename = "src/newData.txt";
			
			List<ImportLine> lines = ReadLinesFromFile(path);
			
			double xav = lines.stream().mapToDouble(val -> val.x).average().orElse(0.0); //odpowiednik LINQ z c#, u�atwia operacje na typowanych kolekcjach
			
			double yav = lines.stream().mapToDouble(val -> val.y).average().orElse(0.0);
			
			String header1 = "Liczba wierszy = " + lines.size();
			String header2 = "Warto�� �rednia kolumny x = " + String.format("%.2f", xav); //formatowanie do dw�ch miejsc po przecinku
			String header3 = "Warto�� �rednia kolumny y = " + String.format("%.2f", yav);
			String header4 = "x | y";
			
			FileWriter writer = new FileWriter(outputFilename, false);
			
			writer.write(header1);
			writer.write(System.lineSeparator());
			
			writer.write(header2);
			writer.write(System.lineSeparator());
			
			writer.write(header3);
			writer.write(System.lineSeparator());
			
			writer.write(header4);
			writer.write(System.lineSeparator());
			
			for (ImportLine line: lines) {
				writer.write(line.toString());
				writer.write(System.lineSeparator());
			}
			
			writer.close();
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
	}

	private static List<ImportLine> ReadLinesFromFile(String path) throws Exception 
	{
		List<ImportLine> result = new ArrayList<ImportLine>();	
		
		Scanner scanner = new Scanner(new File(path));
		
		while(scanner.hasNextLine()) 
		{
			result.add(ParseLine(scanner.nextLine()));
		}
		scanner.close();
		
		return result;
	}

	private static ImportLine ParseLine(String nextLine) throws Exception 
	{
		String[] values = nextLine.split(",");
		
		if(values.length != 2) //odczytano wi�cej ni� 2 liczby z pojedynczego wiersza
			throw new Exception("Missing data in file!");
		
		return new ImportLine(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
	}

}
