import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;


public class LibraryParser {

	String fileToReadName;
	String fileToWriteName;
	String path;
	String outPath ;
	int version = 1;
	public static void main(String[] args){
		LibraryParser lp = new LibraryParser();
		
		lp.path = "D:\\porject\\DataConvertor\\srcfile\\";
		lp.outPath = "D:\\porject\\DataConvertor\\outfile\\";
		lp.version = 1;
		lp.cet4Convert(DataBaseConfigure.DATA_BASE_CET4);
	}
	
	public boolean cet4Convert(String l_name){
		fileToReadName =  l_name + ".txt";
		fileToWriteName = l_name + ".txt";
		Scanner in = null;
		BufferedReader br = null;
		String encoding = "UTF-8";
		try {
			StringParser parser = new Cet4Parser();
			File file = new File(path + fileToReadName);
			InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);
			br = new BufferedReader(read);
            List<Word> wods = new ArrayList<Word>(3456); 
           
            int i = 0;
            Word word;
            String str;
            
            while ((str = br.readLine())!=null) {
                word = parser.parserString(str);
                if(word != null){
                	wods.add(word);
                	++i;
                }
            }
            System.out.println(l_name + "的单词数为： " + i);
            LibraryObject lo = new LibraryObject();
			lo.setLibraryName(fileToReadName);
			lo.setVersion(version);
            lo.setWords(wods); 
            
            br.close();
            
            //
            return createJsonFile(lo);     
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch(Exception e){
        	 e.printStackTrace();
             return false;
        }finally{
       
        }
	}
	
	public FileReader getFile(){
		return null;
	}
	
	public boolean createJsonFile(LibraryObject lo){
		Gson gson = new Gson();
		String json = gson.toJson(lo);
		File file = new File(outPath + fileToWriteName);
		PrintStream ps = null;
		try{
			ps = new  PrintStream(new FileOutputStream(file));
			ps.print(json);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			ps.close();
		}
	}
	
}
