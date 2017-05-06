import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by rajat on 6/5/17.
 */
public class GenerateNFT {
    static Scanner scanner=new Scanner(System.in);

    public  static void main(String[] args) throws Exception {

        String path = getFolderPath();
        enterMarkerFolder(path);

    }

    private static String getFolderPath() throws Exception{
        System.out.println("Enter folder path: ");
        String path = scanner.next();
        return path;
    }

    private static void enterMarkerFolder(String path) {
        File folderPath = new File(path);
        for (File markerFolder : folderPath.listFiles()) {
            if (markerFolder != null && markerFolder.isDirectory()) {
                getImageFile(markerFolder);

            }
        }
    }
    private static void getImageFile(File folderPath){

        for(File file: folderPath.listFiles()){
            System.out.println(file.getName());
            if(file.getName().contains(".png") || file.getName().contains(".jpg")){
                generateNFTFiles(file.getAbsolutePath());
            }
        }
    }

    private static void generateNFTFiles(String filePath){
        //Check Operating System
        String OS = System.getProperty("os.name").toLowerCase();

        if(OS.equals("linux")){
            String[] arg = new String[]
                    {"/bin/bash", "-c", "./genTexData "+ filePath +" -level=4 -leveli=2 -max_dpi=100 -min_dpi=50 -dpi=100"
                        };
        }
        else if(OS.equals("windows")){
            String[] arg = new String[]
                    {"cmd.exe", "/c","start", "genTexData "+ filePath +" -level=4 -leveli=2 -max_dpi=100 -min_dpi=50 -dpi=100"
                    };
        }

        try {
            ProcessBuilder pb = new ProcessBuilder(arg);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process p = pb.start();
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
