package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileController {
	private ArrayList<String> data;
	private File file;

	public FileController(String filename) throws IOException{
		data=new ArrayList<String>();
		this.file = new File(filename);
		loadFile();
	}

	public void saveFile() throws IOException{
		String filename=file.getName().substring(0,file.getName().lastIndexOf("."));
		String directory=file.getParent();
		FileWriter fw = new FileWriter(directory + "/" + filename + "_after.txt");
		for(int i=0;i<data.size();i++){
			String saveData= (i==data.size()-1) ? data.get(i) : (data.get(i) + System.lineSeparator());
			fw.write(saveData);
		}
		fw.close();
	}

	private void loadFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;

		while((line = br.readLine()) != null){
			data.add(line);
		}
		br.close();
	}

	public void setData(ArrayList<String> data){
		this.data=data;
	}

	public ArrayList<String> getData(){
		return this.data;
	}
}
