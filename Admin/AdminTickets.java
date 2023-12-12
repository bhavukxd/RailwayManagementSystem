package Admin;


import java.io.File;
import java.nio.file.Paths;

class AdminTickets{
    static String dir_path = Paths.get(".").toAbsolutePath().normalize().toString();
	static void RecursivePrint(File[] arr, int index, int level,String[] files)
	{
		
		if (index == arr.length)
			return;
		

		
		for (int i = 0; i < level; i++)
			System.out.print("\t");

		
		if (arr[index].isFile())
			files[index] = arr[index].getName().toString().replace(".txt","");
		index+=1;
		RecursivePrint(arr,index, level,files);
	}

	
	static public String[] printFiles()
	{
		
		String maindirpath
			= dir_path+"/tickets/";
		String[] files = new String[100];
		
		File maindir = new File(maindirpath);
		int index = 0;
		if (maindir.exists() && maindir.isDirectory()) {
			
			  
			File arr[] = maindir.listFiles();

			
			RecursivePrint(arr, index, 0,files);
		}
		int i =1;
		for(String file:files){
			if(file!=null){
				System.out.println(Integer.toString(i)+" - "+file);
				i++;
			}
		}
		return files;
	}
}
