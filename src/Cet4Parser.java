
public class Cet4Parser implements StringParser{

	public Word parserString(String buffer) {

        String[] winfo = buffer.split("/");
        if(winfo == null || winfo.length != 3){return null;}
        Word word = new Word();
        word.setWord(winfo[0]);
        word.setPhonogram(winfo[1]);
        word.setParaphrase(winfo[2]);
		return word;
	}

}
