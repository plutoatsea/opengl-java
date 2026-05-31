import org.lwjgl.Version;

public class Main {

    static void main(String[] args) {
        System.out.println("LWJGL Version: "+ Version.getVersion());

        DisplayManager.get().createDisplay();
        while (!DisplayManager.get().isClosed()) {
            DisplayManager.get().updateDisplay();
        }
        DisplayManager.get().closeDisplay();
    }
    
}
