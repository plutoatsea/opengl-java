import java.io.File;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryUtil;

public final class DisplayManager {
    // Default Values
    private String TITLE = "APP";
    private int WIDTH = 300;
    private int HEIGHT = 200;
    
    // long memory address (pointer) to track windows
    private static long window;
    private static DisplayManager instance = null;

    private DisplayManager(){
        configureDisplay();
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

    // Parses XML Data to Configure Window
    private void configureDisplay(){
        try {
            File xmlFile = new File("./config/config.xml");
            if (!xmlFile.exists()) {
                System.out.println("config.xml not found, using default display settings");
                return;
            }
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            var titleNode = doc.getElementsByTagName("title").item(0);
            if (titleNode != null && titleNode.getTextContent() != null) {
                String titleText = titleNode.getTextContent().trim();
                if (!titleText.isEmpty()) {
                    this.TITLE = titleText;
                }
            }
            
            // Parse width with validation
            var widthNode = doc.getElementsByTagName("width").item(0);
            if (widthNode != null && widthNode.getTextContent() != null) {
                try {
                    int parsedWidth = Integer.parseInt(widthNode.getTextContent().trim());
                    if (parsedWidth > 40) {
                        this.WIDTH = parsedWidth;
                    } else {
                        System.out.println("Width must be > 40, using default");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid width value, using default");
                }
            }
            
            // Parse height with validation
            var heightNode = doc.getElementsByTagName("height").item(0);
            if (heightNode != null && heightNode.getTextContent() != null) {
                try {
                    int parsedHeight = Integer.parseInt(heightNode.getTextContent().trim());
                    if (parsedHeight > 40) {
                        this.HEIGHT = parsedHeight;
                    } else {
                        System.out.println("Height must be > 40, using default");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid height value, using default");
                }
            }
            
            System.out.printf("Display configured: %s (%dx%d)%n", TITLE, WIDTH, HEIGHT);
            
        } catch (Exception e) {
            System.err.printf("ERROR: XML Parsing Failed: %s, using default settings%n", e.getMessage());
        }
    }
}