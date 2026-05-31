import org.lwjgl.opengl.GL11;

public class Scene {
    private final Camera camera;
    private final InputHandler input;
    private final Entity ground;

    public Scene(long window, int width, int height) {
        camera = new Camera();
        input  = new InputHandler(window);

        // FLAT GRASS PLANE
        ground = new Entity(0, 0, 0, 10, 10,  0.2f, 0.7f, 0.2f);

        setupProjection(width, height);
    }

    // Called once per frame
    public void update() {
        input.update(camera);
    }

    // Called once per frame after update()
    public void render() {
        // Sky blue background (replaces the dark clear color just for the skybox feel)
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // Apply camera view
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        camera.applyView();

        // Draw world entities
        ground.render();
    }

    // Sets up a basic perspective projection
    private void setupProjection(int width, int height) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        float fov    = 70f;
        float aspect = (float) width / height;
        float near   = 0.1f;
        float far    = 1000f;

        // Manual perspective (no GLU dependency)
        float top   = near * (float) Math.tan(Math.toRadians(fov / 2));
        float right = top * aspect;
        GL11.glFrustum(-right, right, -top, top, near, far);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}