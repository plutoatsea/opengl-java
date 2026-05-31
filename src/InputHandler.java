import org.lwjgl.glfw.GLFW;

public class InputHandler {
    private final long window;
    private double lastMouseX, lastMouseY;
    private boolean firstMouse = true;

    private static final float MOVE_SPEED  = 0.05f;
    private static final float MOUSE_SENS  = 0.1f;

    public InputHandler(long window) {
        this.window = window;
        // Lock cursor to window
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    }

    // Call once per frame; mutates camera directly
    public void update(Camera camera) {
        float rad = (float) Math.toRadians(camera.yaw);
        float forwardX =  (float) Math.sin(rad);
        float forwardZ = -(float) Math.cos(rad);
        // W - FOWARD
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            camera.x += forwardX * MOVE_SPEED;
            camera.z += forwardZ * MOVE_SPEED;
        }
        // S - BACKWARDS
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            camera.x -= forwardX * MOVE_SPEED;
            camera.z -= forwardZ * MOVE_SPEED;
        }
        // A - LEFT
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            camera.x += forwardZ * MOVE_SPEED;
            camera.z -= forwardX * MOVE_SPEED;
        }
        // D - RIGHT
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            camera.x -= forwardZ * MOVE_SPEED;
            camera.z += forwardX * MOVE_SPEED;
        }

        // --- Mouse look ---
        double[] mx = new double[1], my = new double[1];
        GLFW.glfwGetCursorPos(window, mx, my);

        if (firstMouse) { lastMouseX = mx[0]; lastMouseY = my[0]; firstMouse = false; }

        float dx = (float)(mx[0] - lastMouseX) * MOUSE_SENS;
        float dy = (float)(my[0] - lastMouseY) * MOUSE_SENS;
        lastMouseX = mx[0];
        lastMouseY = my[0];

        camera.yaw   += dx;
        camera.pitch += dy;
        camera.pitch  = Math.max(-89, Math.min(89, camera.pitch)); //clamp
    }
}