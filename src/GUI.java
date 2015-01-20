import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
 
public class GUI {

	public int[][][] packages = null;
	
	public Color32[] colors = new Color32[] {
		Color32.red,
		Color32.green,
		Color32.blue
	};
	
	public void draw() {
		if (packages == null)
			return;
			
		try {
			Display.setDisplayMode(new DisplayMode(1000,600));
			Display.setTitle("3D Demo");
		 	Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		GL11.glOrtho(-20, 20, -12, 12, -20, 20);
		
        GL11.glRotatef(25, 1, 0, 0);
		
        int lastX = Mouse.getX();
        int lastY = Mouse.getY();
        boolean mouseClicked = false;
        
		while (!Display.isCloseRequested()) {
			Display.update();
	          
	         GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT|GL11.GL_COLOR_BUFFER_BIT);
	         GL11.glEnable(GL11.GL_DEPTH_TEST);
	         
	         if (Mouse.isButtonDown(0)) {
	        	 if (!mouseClicked) {
	        		 mouseClicked = true;
	        		 lastX = Mouse.getX();
	        		 lastY = Mouse.getY();
	        	 }

		         GL11.glRotatef(Mouse.getX() - lastX, 0, 1, 0);
		         // GL11.glRotatef(lastY - Mouse.getY(), 1, 0, 0);
		         
		         lastX = Mouse.getX();
		         lastY = Mouse.getY();
	         } else {
	        	 mouseClicked = false;
	         }
	         
	         GL11.glBegin(GL11.GL_QUADS);
	         
	         drawBox(0,-0.6f,0,
	        		 33,0.2f,8,Color32.white);
	         drawPackages();
	         
	         GL11.glLoadIdentity();
	         GL11.glEnd();
	     }
	     
	     Display.destroy();
	     System.exit(0);
	 }
	
	private void drawPackages() {
		if (packages == null)
			return;
		
		for (int z = 0; z < packages[0][0].length; z++) {
			for (int y = 0; y < packages[0].length; y++) {
				for (int x = 0; x < packages.length; x++) {
					if (packages[x][y][z] != 0)
						drawBox(x - packages.length/2,
								y,
								z - packages[0][0].length/2 + 0.5f,
								1f, 1f, 1f,
								colors[packages[x][y][z] - 1]);
				}
			}
		}
	}
    
    private void drawBox(float x, float y, float z, float sx, float sy, float sz, Color32 color) {
    	sx = sx/2;
    	sy = sy/2;
    	sz = sz/2;
    	
        GL11.glColor3f(color.r, color.g, color.b);
        GL11.glVertex3f(x + sx, y + sy, z - sz);
        GL11.glVertex3f(x - sx, y + sy, z - sz);
        GL11.glVertex3f(x - sx, y + sy, z + sz);
        GL11.glVertex3f(x + sx, y + sy, z + sz);
        
        color = color.changeBrightness(0.5f);
        GL11.glColor3f(color.r, color.g, color.b);
        GL11.glVertex3f(x + sx, y + sy, z + sz);
        GL11.glVertex3f(x - sx, y + sy, z + sz);
        GL11.glVertex3f(x - sx, y - sy, z + sz);
        GL11.glVertex3f(x + sx, y - sy, z + sz);
        
        color = color.changeBrightness(0.5f);
        GL11.glColor3f(color.r, color.g, color.b);
        GL11.glVertex3f(x + sx, y - sy, z + sz);
        GL11.glVertex3f(x - sx, y - sy, z + sz);
        GL11.glVertex3f(x - sx, y - sy, z - sz);
        GL11.glVertex3f(x + sx, y - sy, z - sz);
        
        GL11.glVertex3f(x + sx, y + sy, z - sz);
        GL11.glVertex3f(x + sx, y + sy, z + sz);
        GL11.glVertex3f(x + sx, y - sy, z + sz);
        GL11.glVertex3f(x + sx, y - sy, z - sz);
        
        GL11.glVertex3f(x - sx, y + sy, z + sz);
        GL11.glVertex3f(x - sx, y + sy, z - sz);
        GL11.glVertex3f(x - sx, y - sy, z - sz);
        GL11.glVertex3f(x - sx, y - sy, z + sz);
        
        GL11.glVertex3f(x + sx, y - sy, z - sz);
        GL11.glVertex3f(x - sx, y - sy, z - sz);
        GL11.glVertex3f(x - sx, y + sy, z - sz);
        GL11.glVertex3f(x + sx, y + sy, z - sz);
    }
}