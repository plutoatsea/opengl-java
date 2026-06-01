import org.lwjgl.opengl.GL11;

class Cube extends Entity {
    private float size;
    
    public Cube(float x, float y, float z, float size, float r, float g, float b) {
        super(x, y, z, r, g, b);
        this.size = size;
    }
    
    @Override
    void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(yaw, 0, 1, 0);
        GL11.glRotatef(pitch, 1, 0, 0);
        
        GL11.glColor3f(r, g, b);
        
        float half = size / 2;
        
        GL11.glBegin(GL11.GL_QUADS);
        
        // Front face
        GL11.glVertex3f(-half, -half,  half);
        GL11.glVertex3f( half, -half,  half);
        GL11.glVertex3f( half,  half,  half);
        GL11.glVertex3f(-half,  half,  half);
        
        // Back face
        GL11.glVertex3f(-half, -half, -half);
        GL11.glVertex3f(-half,  half, -half);
        GL11.glVertex3f( half,  half, -half);
        GL11.glVertex3f( half, -half, -half);
        
        // Top face
        GL11.glVertex3f(-half,  half, -half);
        GL11.glVertex3f(-half,  half,  half);
        GL11.glVertex3f( half,  half,  half);
        GL11.glVertex3f( half,  half, -half);
        
        // Bottom face
        GL11.glVertex3f(-half, -half, -half);
        GL11.glVertex3f( half, -half, -half);
        GL11.glVertex3f( half, -half,  half);
        GL11.glVertex3f(-half, -half,  half);
        
        // Right face
        GL11.glVertex3f( half, -half, -half);
        GL11.glVertex3f( half,  half, -half);
        GL11.glVertex3f( half,  half,  half);
        GL11.glVertex3f( half, -half,  half);
        
        // Left face
        GL11.glVertex3f(-half, -half, -half);
        GL11.glVertex3f(-half, -half,  half);
        GL11.glVertex3f(-half,  half,  half);
        GL11.glVertex3f(-half,  half, -half);
        
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}