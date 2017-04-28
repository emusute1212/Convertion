package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FullToHalfString {
	private FileController file;

	public static void main(String[] args) {
		FullToHalfString main = new FullToHalfString();
		main.init();
		main.file.setData(main.convert());
		try {
			main.file.saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init(){
		System.out.print("File path:");
		Scanner scan = new Scanner(System.in);
		String filename = scan.next();
		try {
			file=new FileController(filename);
		} catch (IOException e) {
			file=null;
			e.printStackTrace();
		}
	}

	public ArrayList<String> convert(){
		ArrayList<String> beforeData=file.getData();
		ArrayList<String> afterData=new ArrayList<String>();

		for(String line:beforeData){
			String output="";
			for(char character:line.toCharArray()){
				int code=(int)character;
				//変換対象
				if((code >= 0x0030) && (code <= 0x007A)){
					code^=0xFF00;
					code-=0x0020;
				}
				output+=(char)code;
			}
			afterData.add(output);
		}

		return afterData;
	}

}
