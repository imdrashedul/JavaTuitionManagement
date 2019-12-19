package library;

import java.io.*;
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.Date;

public class FileManager {
	private String file;
    private String path;

	public FileManager(String file) {
		this.file = file;
        this.path = "";
	}

    public FileManager(String file, String path) {
        this(file);
        this.path = path;
    }

	public void setPath(String path) {
		this.path = path;
	}

    public void setFile(String file) {
        this.file = file;
    }

	public String read() throws IOException
    {
    	FileInputStream input;
        String rtn = null;

    	File __file = new File(path+file); 

    	if(__file.exists()) {
    		input = new FileInputStream(__file);
			byte[] fdata = new byte[(int) __file.length()];
			input.read(fdata);
			input.close();
			rtn = new String(fdata, "UTF-8");
		}

        return rtn;
    }

	public void write(String data, boolean append) throws IOException
    {
        BufferedWriter output;
        mkdir();
    	File __file = new File(path+file);
    	__file.createNewFile();
        output = new BufferedWriter(new FileWriter(__file, append));
        if(data==null)
            output.write("");
        else
            output.write(data);
        output.close();
    }

    public String copy(String from, boolean rename) throws IOException {
        Path _from = Paths.get(from);
        String filename = _from.getFileName().toString();
        this.file = rename ? generateFileName(filename) : filename;
        mkdir();
        File fileFrom = new File(from);
        File fileTo = new File(path+file);
        
        if(fileFrom.exists()) {
            fileTo.createNewFile(); 

            FileInputStream input = new FileInputStream(fileFrom);
            FileOutputStream output = new FileOutputStream(fileTo);
            byte[] fdata = new byte[(int) fileFrom.length()];
            input.read(fdata);
            input.close();
            output.write(fdata, 0, (int) fileFrom.length());
            output.close();
        }

        return this.file;
    }

    public void mkdir() {
        if(path!=null && !path.equals("")) {
            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdir();
            }
        }
    }

    public String generateFileName(String source) {
        Date date = new Date();
        String name = HashManager.md5(String.valueOf(date.getTime()));
               name += "." + extension(source);
        return name;
    }

    public String extension(String filename) {
        if (filename == null) {
            return null;
        }

        int extensionPos = filename.lastIndexOf('.');
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);

        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
}