import java.nio.IntBuffer;

import org.lwjgl.assimp.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

public class Model extends Entity {
    private float[] vertexArray;
    private int vertexCount;
    
    public Model(float x, float y, float z, String modelPath, float r, float g, float b) {
        super(x, y, z, r, g, b);
        loadModelSimple(modelPath);
    }
    
    private void loadModelSimple(String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            AIScene scene = Assimp.aiImportFile(path, Assimp.aiProcess_Triangulate);
            
            if (scene == null) {
                System.err.println("Failed: " + Assimp.aiGetErrorString());
                return;
            }
            
            AIMesh mesh = AIMesh.create(scene.mMeshes().get(0));
            AIVector3D.Buffer vertices = mesh.mVertices();
            AIFace.Buffer faces = mesh.mFaces();
            
            // Calculate total vertices (3 per triangle)
            int totalVertices = 0;
            for (int i = 0; i < faces.remaining(); i++) {
                totalVertices += faces.get(i).mNumIndices();
            }
            
            vertexArray = new float[totalVertices * 3];
            vertexCount = 0;
            
            for (int i = 0; i < faces.remaining(); i++) {
                AIFace face = faces.get(i);
                IntBuffer indices = face.mIndices();
                
                for (int j = 0; j < indices.remaining(); j++) {
                    int vertexIndex = indices.get(j);
                    AIVector3D vert = vertices.get(vertexIndex);
                    
                    vertexArray[vertexCount++] = vert.x();
                    vertexArray[vertexCount++] = vert.y();
                    vertexArray[vertexCount++] = vert.z();
                }
            }
            
            Assimp.aiReleaseImport(scene);
        }
    }
    
    @Override
    void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(yaw, 0, 1, 0);
        GL11.glRotatef(pitch, 1, 0, 0);
        GL11.glScalef(sX, sY, sZ);
        GL11.glColor3f(r, g, b);
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        
        // Use vertex array for better performance
        java.nio.FloatBuffer vertexBuffer = org.lwjgl.BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray);
        vertexBuffer.flip();
        
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount / 3);
        
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glPopMatrix();
    }
}
