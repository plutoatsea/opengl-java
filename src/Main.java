import org.lwjgl.Version;

public class Main {

    static void main(String[] args) {
        System.out.println("LWJGL Version: "+ Version.getVersion());

        DisplayManager.createDisplay();
        while (!DisplayManager.isCloseRequested()) {
            DisplayManager.updateDisplay();
        }
        DisplayManager.closeDisplay();
    }
    
}