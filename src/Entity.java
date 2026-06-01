public abstract class Entity {
    protected float x, y, z;       // Position
    protected float r, g, b;       // Color
    protected float sX, sY, sZ = 1.0f; //Scaling
    protected float yaw, pitch = 0;// Potation x&y axis
    protected boolean canCollide = true; //noclip

    public Entity(float x, float y, float z, float r, float g, float b){
        this.x = x; this.y = y; this.z = z;
        this.r = r; this.g = g; this.b = b;
    };

    // Draws on screen
    abstract void render();
    //Custom Rotation
    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
    //Equal Scale
    public void setScale(float scale) {
        this.sX = scale;
        this.sY = scale;
        this.sZ = scale;
    }
    //Custom Scale
    public void setScale(float scaleX, float scaleY, float scaleZ) {
        this.sX = scaleX;
        this.sY = scaleY;
        this.sZ = scaleZ;
    }
}