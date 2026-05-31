import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryUtil;

public final class DisplayManager {

    private static int WIDTH;
    private static int HEIGHT;
    private static final String TITLE = "APP";
    private static DisplayManager instance = null;
    
    // long memory address (pointer) to track windows
    private static long window;

    private DisplayManager(){
        WIDTH = 1280; HEIGHT = 720;
    }

    public static DisplayManager get(){
        if (instance == null){
            instance = new DisplayManager();
        }
        return instance;
    }

    public void createDisplay() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        // Create the window
        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        // Window Position
        centerDisplay();
        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);
    }

    // Moves the Window to the Center of the Screen.
    public void centerDisplay(){
        GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        if (vidmode != null) {
            GLFW.glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
            );
        }
    }

    // Updates display by swapping buffers and polling input events
    public void updateDisplay() {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    // Returns true if User Quits
    public boolean isClosed() {
        return GLFW.glfwWindowShouldClose(window);
    }

    // Cleans before shutdown
    public void closeDisplay() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}