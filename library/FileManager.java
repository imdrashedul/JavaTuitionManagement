package library;

import java.io.*;

public class FileManager {
	private String path;

	public FileManager(String path) {
		this.path = path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String read() throws IOException
    {
    	FileInputStream input;
        String rtn = null;

    	File file = new File(path); 

    	if(file.exists()) {
    		input = new FileInputStream(file);
			byte[] fdata = new byte[(int) file.length()];
			input.read(fdata);
			input.close();
			rtn = new String(fdata, "UTF-8");
		}

        return rtn;
    }

	public void write(String data, boolean append) throws IOException
    {
        BufferedWriter output; 
    	File file = new File(path);
    	file.createNewFile();
        output = new BufferedWriter(new FileWriter(file, append));
        if(data==null)
            output.write("");
        else
            output.write(data);
        output.close();
    }

}