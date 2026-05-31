import org.lwjgl.Version;
 
public class Main {

    static void main(String[] args) {
        System.out.println("LWJGL Version: "+ Version.getVersion());
        DisplayManager display = DisplayManager.get();

        display.createDisplay();

        Scene scene = new Scene(display.getWindow(), display.getWidth(), display.getHeight());

        while (!display.isClosed()) {
            scene.update();
            scene.render();
            display.updateDisplay();
        }
        display.closeDisplay();
    }

}