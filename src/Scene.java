import java.util.List;
import org.lwjgl.opengl.GL11;

import objects.*;

public class Scene {
    private final Camera camera;
    private final InputHandler input;
    private ObjectManager objm;

    public Scene(long window, int width, int height) {
        camera = new Camera();
        input  = new InputHandler(window);
        objm = new ObjectManager();
        objm.addAll(List.of(
            new Plane(0, 0, 0, 10, 10, 0.1f, 0.5f, 0.2f),
            new Plane(10f, 5f, 0f, 10f, 5f, 0.1f, 0.1f, 0.1f),
            new Cube(2, 1, 2, 1.0f, 0.9f, 0.5f, 0.0f),
            new Model(2,1.75f,2,"res/models/teddy.obj",1f,1f,1f)
        ));
        objm.get(1).setRotation(90, 90); //Black Wall Entity
        objm.get(3).setScale(0.01f);
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
        objm.renderAll();
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