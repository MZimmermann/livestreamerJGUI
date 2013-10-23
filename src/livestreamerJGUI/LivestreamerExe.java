package livestreamerJGUI;

import java.util.Scanner;


public class LivestreamerExe {
    private Process proc;
    
    LivestreamerExe (){
    }
    
    public void runLivestreamer(String[] cmd, final javax.swing.JTextArea ta){
        ProcessBuilder pb = new ProcessBuilder(cmd);
        try {
            proc = pb.start();
            final Scanner in = new Scanner(proc.getInputStream());
            new Thread() {
                public void run() {
                    while (in.hasNextLine())
                        ta.append(in.nextLine() +"\n" +"------" +"\n");
                }
            }.start();
            final Scanner in2 = new Scanner(proc.getErrorStream());
            new Thread() {
                public void run() {
                    while (in2.hasNextLine())
                        ta.setText(in2.nextLine());
                }
            }.start();
        }
        catch (Exception err){
        }
    }
    public void killLivestreamer(){
        proc.destroy();
    }
    public void nullProc(){
        new Thread() {
            public void run() {
                try{
                    proc.waitFor();
                }
                catch (Exception err){
                }
                proc = null;
            }
        }.start();
    }
    
    public Process getProc(){
        return proc;
    }
}
