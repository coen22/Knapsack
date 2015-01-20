public class Color32 {
	
	public float r;
	public float g;
	public float b;
	public float a;
	
	public static final Color32 white = new Color32(1, 1, 1);
	public static final Color32 red = new Color32(1, 0, 0);
	public static final Color32 green = new Color32(0, 1, 0);
	public static final Color32 blue = new Color32(0, 0, 1);
	public static final Color32 black = new Color32(0, 0, 0);
	
	public Color32(float r, float g, float b) {
		this.r = Math.min(Math.max(r, 0), 1);
		this.g = Math.min(Math.max(g, 0), 1);
		this.b = Math.min(Math.max(b, 0), 1);
		a = 1;
	}
	
	public Color32(float r, float g, float b, float a) {
		this.r = Math.min(Math.max(r, 0), 1);
		this.g = Math.min(Math.max(g, 0), 1);
		this.b = Math.min(Math.max(b, 0), 1);
		this.a = Math.min(Math.max(a, 0), 1);
	}
	
	public Color32 changeBrightness(float perc) {
		return new Color32(r * perc, g * perc, b * perc, a);
	}
}
