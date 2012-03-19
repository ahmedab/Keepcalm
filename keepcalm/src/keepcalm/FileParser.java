package keepcalm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileParser {
	private String fileName;
	private ArrayList<ArrayList> data;

	public FileParser(String fileName) throws IOException {
		this.fileName = fileName;
		data = new ArrayList<ArrayList>();

		FileInputStream fis = null;
		DataInputStream dis;
		BufferedReader br;

		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br  = new BufferedReader(new InputStreamReader(dis));

			String line;
			while ((line = br.readLine()) != null) {
				data.add(parseLine(line));
			}
		} finally {
			if (fis != null)
				fis.close();
		}
	}

	/**
	 * @param instanceNumber The instance number is the same as in 
	 * 						 the text file, so it starts with one (1)
	 * @return A list containing the values for that line
	 */
	public ArrayList<String> getInstance(int instanceNumber) {
		return data.get(instanceNumber-1);
	}

	private ArrayList<String> parseLine(String inputLine) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(inputLine, "%");
		if (!st.hasMoreElements()) {
			throw new IOException("Missing '%' character at the end of the line!");
		}
		String line = st.nextToken();
		st = new StringTokenizer(line, ",");
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		list.remove(list.size()-1);
		return list;
	}
}
