/**
 * The Class Color32.
 */
public class Color32 {
	
	/** The red value */
	public float r;
	
	/** The green value */
	public float g;
	
	/** The blue value */
	public float b;
	
	/** The alpha value */
	public float a;
	
	/** A hard-coded white templete */
	public static final Color32 white = new Color32(1, 1, 1);
	
	/** A hard-coded red templete */
	public static final Color32 red = new Color32(1, 0, 0);
	
	/** A hard-coded green templete */
	public static final Color32 green = new Color32(0, 1, 0);
	
	/** A hard-coded blue templete */
	public static final Color32 blue = new Color32(0, 0, 1);
	
	/** A hard-coded black templete */
	public static final Color32 black = new Color32(0, 0, 0);
	
	/**
	 * Instantiates a new color
	 *
	 * @param r the red
	 * @param g the green
	 * @param b the blue
	 */
	public Color32(float r, float g, float b) {
		this.r = Math.min(Math.max(r, 0), 1);
		this.g = Math.min(Math.max(g, 0), 1);
		this.b = Math.min(Math.max(b, 0), 1);
		a = 1;
	}
	
	/**
	 * Instantiates a new color
	 *
	 * @param r the red
	 * @param g the green
	 * @param b the blue
	 * @param a the alpha
	 */
	public Color32(float r, float g, float b, float a) {
		this.r = Math.min(Math.max(r, 0), 1);
		this.g = Math.min(Math.max(g, 0), 1);
		this.b = Math.min(Math.max(b, 0), 1);
		this.a = Math.min(Math.max(a, 0), 1);
	}
	
	/**
	 * A method to change the brightness
	 *
	 * @param perc the percentage that is will be brighter
	 * @return a new color32
	 */
	public Color32 changeBrightness(float perc) {
		return new Color32(r * perc, g * perc, b * perc, a);
	}
}
