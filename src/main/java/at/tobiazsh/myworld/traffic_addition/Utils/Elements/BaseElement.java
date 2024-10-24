package at.tobiazsh.myworld.traffic_addition.Utils.Elements;


/*
 * @created 19/10/2024 (DD/MM/YYYY) - 23:31
 * @project MyWorld Traffic Addition
 * @author Tobias
 */


import java.util.HashMap;
import java.util.Map;

public abstract class BaseElement {
	protected float x, y, width, height, factor, rotation;
	public boolean clicked = false;
	public String id, name;
	public static Map<String, BaseElement> Ids = new HashMap<>();
	private static int nextId = 0;

	public BaseElement(float x, float y, float width, float height, float factor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.factor = factor;
		registerId(this);
	}

	private String registerId(BaseElement element) {
		this.id = "ELEMENT_" + nextId;
		Ids.put(id, element);
		nextId++;
		this.name = id;
		return id;
	}

	public String getId() {
		return id;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public abstract void renderImGui(); // Abstract render method to be implemented by subclasses
	public abstract void renderMinecraft();

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	// Separate methods for x and y to save a little bit of memory
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setSize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float calcBlocks(float value) {
		return value / this.factor;
	}

	public static float calcBlocks(float value, float factor) {
		return value / factor;
	}

	public void scalePercentSize(float percent) {
		this.width = this.width + (this.width * (percent / 100));
	}

	public void scaleSize(float pixels, boolean scaleByHeight) {
		float oldVal = scaleByHeight ? this.height : this.width;
		float val = oldVal;

		val += pixels;

		float factor = val / oldVal;

		float oppositeVal = !scaleByHeight ? this.height : this.width;

		float newVal = val;
		float newOppositeVal = oppositeVal * factor;

		if (scaleByHeight) {
			this.height = newVal;
			this.width = newOppositeVal;
		} else {
			this.width = newVal;
			this.height = newOppositeVal;
		}
	}

	public void scaleHeight(float pixels) {
		this.height += pixels;
	}

	public void scaleWidth(float pixels) {
		this.width += pixels;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public void setRotation(float angle) {
		this.rotation = angle;
	}

	public float getRotation() {
		return rotation;
	}
}
