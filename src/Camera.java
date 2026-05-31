import org.lwjgl.opengl.GL11;

public class Camera {
    public float x = 0, y = 2, z = 0;
    public float yaw   = 0;
    public float pitch = 20;

    // Applies view transform: rotate then translate (inverse of camera pose)
    public void applyView() {
        GL11.glRotatef(pitch, 1, 0, 0);
        GL11.glRotatef(yaw,   0, 1, 0);
        GL11.glTranslatef(-x, -y, -z);
    }
}