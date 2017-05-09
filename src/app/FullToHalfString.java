package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * メインクラス
 */
public class FullToHalfString {
	/**
	 * FileControllerクラスのインスタンス
	 */
	private FileController file;

	/**
	 * メインメソッド
	 * @param args 今回は使用しない
	 */
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

	/**
	 * 初期化クラス
	 */
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

	/**
	 * 半角文字から全角文字へ変換するメソッド
	 * @return
	 */
	public ArrayList<String> convert(){
		ArrayList<String> beforeData=file.getData();
		ArrayList<String> afterData=new ArrayList<String>();

		for(String line:beforeData){
			String output="";
			for(char character:line.toCharArray()){
				int code=(int)character;
				//変換対象(半角英数の時)
				if((code >= 0x0021) && (code <= 0x007E)){
					code^=0xFF00;
					code-=0x0020;
				}
				//変換対象(半角スペースの時)
				if(code == 0x0020){
					code=0x3000;
				}
				output+=(char)code;
			}
			afterData.add(output);
		}

		return afterData;
	}

}
