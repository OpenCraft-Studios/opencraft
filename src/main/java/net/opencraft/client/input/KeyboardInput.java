package net.opencraft.client.input;

import java.util.HashSet;
import java.util.Set;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardInput extends GLFWKeyCallback {

	public Set<Integer> pressedKeys = new HashSet<>();
	public Set<Integer> justPressedKeys = new HashSet<>();
	public Set<Integer> justReleasedKeys = new HashSet<>();

	public KeyboardInput(long window) {
		GLFW.glfwSetKeyCallback(window, this);
	}

	public void poll() {
        justPressedKeys.clear();
        justReleasedKeys.clear();
    }

	public boolean isKeyPressed(int key) {
        return pressedKeys.contains(key);
    }

	public boolean isKeyJustPressed(int key) {
        return justPressedKeys.contains(key);
    }

	public boolean isKeyJustReleased(int key) {
        return justReleasedKeys.contains(key);
    }

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if (action == GLFW.GLFW_PRESS) {
			pressedKeys.add(key);
			justPressedKeys.add(key);
		} else if (action == GLFW.GLFW_RELEASE) {
			if (pressedKeys.remove(key)) {
				justReleasedKeys.add(key);
			}
		} else if (action == GLFW.GLFW_REPEAT) {
			pressedKeys.add(key);
		}
	}
}
