public abstract class Entity {
    protected float x, y, z;       // Position
    protected float r, g, b;       // Color
    protected float yaw, pitch = 0;// Potation x&y axis
    protected boolean canCollide = true; //noclip

    public Entity(float x, float y, float z, float r, float g, float b){
        this.x = x; this.y = y; this.z = z;
        this.r = r; this.g = g; this.b = b;
    };

    // Draws on screen
    abstract void render();

    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
}