package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ファイルを操作するクラス
 */
public class FileController {
	/**
	 * ファイルから読み込んだデータ
	 */
	private ArrayList<String> data;
	/**
	 * Fileクラスのインスタンス
	 */
	private File file;

	/**
	 * コンストラクタ
	 * @param filename 読み込みたいファイル名
	 * @throws IOException ファイルのI/Oエラー
	 */
	public FileController(String filename) throws IOException{
		data=new ArrayList<String>();
		this.file = new File(filename);
		loadFile();
	}

	/**
	 * ファイルを保存するメソッド
	 * <br>setDataメソッドで設定されたデータをテキストファイルで保存する</br>
	 * @throws IOException ファイルのI/Oエラー
	 */
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

	/**
	 * ファイルを読み込むメソッド
	 * <br>読み込まれたデータはgetDataメソッドで取り出し可能</br>
	 * @throws IOException ファイルのI/Oエラー
	 */
	private void loadFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;

		while((line = br.readLine()) != null){
			data.add(line);
		}
		br.close();
	}

	/**
	 * セーブしたいデータを設定する
	 * @param data 保存したいデータ
	 */
	public void setData(ArrayList<String> data){
		this.data=data;
	}

	/**
	 * 読み込んだデータを取得するためのメソッド
	 * @return 読み込んだデータ
	 */
	public ArrayList<String> getData(){
		return this.data;
	}
}
