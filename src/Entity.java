import org.lwjgl.opengl.GL11;

public class Entity {
    private float x, y, z;       // position
    private float width, depth;  // plane
    private float r, g, b;       // Color

    public Entity(float x, float y, float z, float width, float depth, float r, float g, float b) {
        this.x = x; this.y = y; this.z = z;
        this.width = width; this.depth = depth;
        this.r = r; this.g = g; this.b = b;
    }

    // Renders a flat colored quad on the XZ plane
    public void render() {
        GL11.glColor3f(r, g, b);
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex3f(x - width, y, z + depth);
            GL11.glVertex3f(x + width, y, z + depth);
            GL11.glVertex3f(x + width, y, z - depth);
            GL11.glVertex3f(x - width, y, z - depth);
        GL11.glEnd();
    }
}