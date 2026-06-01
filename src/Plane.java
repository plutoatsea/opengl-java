import org.lwjgl.opengl.GL11;

class Plane extends Entity{
    private float width, depth;
    public Plane(float x, float y, float z, float width, float depth, float r, float g, float b){
        super(x,y,z,r,g,b);
        this.width = width; this.depth = depth;
    }

    @Override
    void render() {
        GL11.glDisable(GL11.GL_CULL_FACE);  //Make the other side not transparent
        //Rotate
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(yaw, 0, 1, 0);
        GL11.glRotatef(pitch, 1, 0, 0);
        //Create
        GL11.glColor3f(r, g, b);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(-width, 0, depth);
        GL11.glVertex3f(width, 0, depth);
        GL11.glVertex3f(width, 0, -depth);
        GL11.glVertex3f(-width, 0, -depth);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_CULL_FACE); //enables back
    }
}