package EnDecrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class EnDecrypt{
	public static BASE64Encoder base64en = new BASE64Encoder();
	public static BASE64Decoder base64de = new BASE64Decoder();
	
	public static String prefix = "E:\\gitrepo\\ACTtest\\act\\tmp\\";
	public static String outputprefix = "E:\\gitrepo\\ACTtest\\act\\";
	public static String outputprefix1 = "E:\\gitrepo\\ACTtest\\act\\output1\\";
	public static String[] TESTPATH = {"resources\\2005 April 60E\\","resources\\2005 Dec 63C\\","resources\\2005 June 61D\\"
			,"resources\\2006 April 63E\\","resources\\2006 June 63F\\","resources\\2007 Dec 65E\\",
			"resources\\2007 June 65C\\","resources\\2008 April 65D\\","resources\\2008 Dec 67A\\",
			"resources\\2009 April 66F\\","resources\\2009 Dec 68A\\","resources\\2010 April 68G\\",
			"resources\\2011 April 67F\\","resources\\2011 June 69F\\","resources\\2012 April 70G\\",
			"resources\\2012 June 70C\\"};
	 public static char[] ChineseInterpunction = { '“', '”', '‘', '’', '。', '，', '；', '：','？', '！', '—', '～', '（', '）','《','》' };   
     public static char[] EnglishInterpunction = { '"', '"', '\'', '\'', '.', ',', ';', ':', '?', '!',  '-', '~', '(', ')', '<', '>' };
	public static String[] FILENAME = {"English\\1.txt","English\\2.txt","English\\3.txt","English\\4.txt","English\\5.txt",
			"Math\\math.txt",
			"Reading\\1.txt","Reading\\2.txt","Reading\\3.txt","Reading\\4.txt",
			"Science\\1.txt","Science\\2.txt","Science\\3.txt","Science\\4.txt","Science\\5.txt","Science\\6.txt","Science\\7.txt"};
    
	public static String ToNoch(String input,String filename,int line) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
        	for(int j=0;j<ChineseInterpunction.length;j++){
        		if(c[i]==ChineseInterpunction[j]){
        			c[i] = EnglishInterpunction[j];
        			System.out.println(filename);
              	  	System.out.println(line+","+c[i]);
        		}
        	}
        	
        }
        String returnString = new String(c);
   		return returnString;
    }
	
	public static String ToDBC(String input,String filename,int line) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
          boolean flag = false;
        	for(int j=0;j<ChineseInterpunction.length;j++){
        		if(c[i]==ChineseInterpunction[j]){
        			c[i] = EnglishInterpunction[j];
        			System.out.println(filename);
              	  	System.out.println(line+","+c[i]);
              	  	flag = true;
        		}
        	}
        	if(flag)continue;
        	if (c[i] == '\u3000') {
            c[i] = ' ';
            int k = c[i];
      	  
      	  System.out.println(filename+",Space");
      	  System.out.println(line);
            
          } 
          /*else if(c[i] == '＄'){        	  int k = c[i];
        	  int k1 = '$';
        	  System.out.println(k+","+(k-k1));
        	  c[i] = (char)k1;
          }*/
          else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
        	  int k = c[i];
        	  char k1 = (char)(k-65248);
        	  System.out.println(filename+","+k+","+(k-k1));
        	  System.out.println(line+","+k1);
        	  c[i] = k1;
          }
        }
        String returnString = new String(c);
   		return returnString;
    }
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
          if (c[i] == ' ') {
            c[i] = '\u3000';
          } else if (c[i] < '\177') {
            c[i] = (char) (c[i] + 65248);

          }
        }
        return new String(c);
}
    public static String ToNochfile(String fileName) {
        String res = "";
        try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			BufferedReader reader = new BufferedReader(read);
	        try {
	            
	            //reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                //System.out.println("line " + line + ": " + tempString);
	                tempString = ToNoch(tempString,fileName,line);
	            	res+=tempString+"\r\n";
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }

		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
                
        return res;
	}
    public static String ToDBCFile(String fileName) {
        File file = new File(fileName);
        String res = "";
        try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			BufferedReader reader = new BufferedReader(read);
	        try {
	            
	            //reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                //System.out.println("line " + line + ": " + tempString);
	                tempString = ToDBC(tempString,fileName,line);
	            	res+=tempString+"\r\n";
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }

		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
                
        return res;
	}
    
	public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        String res = "";
        
        InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			BufferedReader reader = null;
	        
	        try {
	        	reader = new BufferedReader(read);
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                //System.out.println("line " + line + ": " + tempString);
	                //tempString = ToDBC(tempString);
	            	res+=base64en.encode(tempString.getBytes()).replaceAll("\r\n", "")+"\r\n";
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }

		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
                
        return res;
	}
	public static String transFileByLines(String fileName) {
        File file = new File(fileName);
        String res = "";
        BufferedReader reader = null;
        try {
            
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                res+= new String(base64de.decodeBuffer(tempString));
                res+='\n';
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        return res;
	}
	
	public static void writeFile(String str,String filename) {  
		
        try {  
            FileOutputStream out = new FileOutputStream(filename); // 输出文件路径  
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            osw.write(str);  
            osw.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	public static void ToNoCh(){
		for(int i=0;i<TESTPATH.length;i++){
			for(int j=0;j<FILENAME.length;j++){
				String filename = prefix + TESTPATH[i]+FILENAME[j];
				String filename1 = outputprefix1 + TESTPATH[i] +FILENAME[j]; 
				String dbc = ToNochfile(filename);
				writeFile(dbc,filename1);
			}
		}
	}
	public static void ToDBC(){
		for(int i=0;i<TESTPATH.length;i++){
			for(int j=0;j<FILENAME.length;j++){
				String filename = prefix + TESTPATH[i]+FILENAME[j];
				String filename1 = outputprefix1 + TESTPATH[i] +FILENAME[j]; 
				String dbc = ToDBCFile(filename);
				writeFile(dbc,filename1);
			}
		}
	}
	public static void TO64(){
		for(int i=0;i<TESTPATH.length;i++){
			for(int j=0;j<FILENAME.length;j++){
				//String filename = prefix + TESTPATH[i]+FILENAME[j];
				String filename1 = outputprefix1 + TESTPATH[i] +FILENAME[j];
				String outfilename = outputprefix + TESTPATH[i]+FILENAME[j];
				String s = readFileByLines(filename1);
				writeFile(s,outfilename);
				
			}
		}
	}
	public static void main(String[] args){
		ToNoCh();
		ToDBC();
		TO64();
	}
}