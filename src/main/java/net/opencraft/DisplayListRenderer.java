package net.opencraft;

import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public final class DisplayListRenderer {

	private final IntBuffer buffer;

	private int x, y, z;
	private float offsetX, offsetY, offsetZ;

	private boolean initialized;
	private boolean readyToRender;

	private DisplayListRenderer(int size) {
		this.buffer = BufferUtils.createIntBuffer(size);
		this.initialized = false;
		this.readyToRender = false;
	}
	
	public static DisplayListRenderer createFixed(int size) {
		return new DisplayListRenderer(size);
	}
	
	public static DisplayListRenderer[] createFixedArray(int array_size, int size) {
		DisplayListRenderer[] array = new DisplayListRenderer[array_size];
		for (int i = 0; i < array_size; i++) {
			array[i] = createFixed(size);
		}
		return array;
	}

	public void initialize(int x, int y, int z, float offsetX, float offsetY, float offsetZ) {
		this.initialized = true;
		this.buffer.clear();
		this.x = x;
		this.y = y;
		this.z = z;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}

	public boolean isInitializedAt(int x, int y, int z) {
		if (!isInitialized())
			return false;

		return this.x == x && this.y == y && this.z == z;
	}

	public void a(final int integer) {
		buffer.put(integer);
		if (!buffer.hasRemaining())
			this.render();
	}

	public void render() {
		if (!isInitialized())
			return;

		if (!isReadyToRender()) {
			buffer.flip();
			readyToRender = true;
		}

		if (!buffer.hasRemaining())
			return;

		glPushMatrix();
		{
			glTranslatef(x - offsetX, y - offsetY, z - offsetZ);
			glCallLists(buffer);
		}
		glPopMatrix();
	}

	public void reset() {
		this.initialized = false;
		this.readyToRender = false;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public boolean isReadyToRender() {
		return readyToRender;
	}

}
