package net.opencraft.renderer.gui;

import static net.opencraft.OpenCraft.*;

import java.awt.Dimension;

public class GUI {

	public static Dimension createScaledResolution() {
		Dimension resolution = new Dimension(oc.width, oc.height);
		GUI.scale(resolution);

		oc.scaledWidth = resolution.width;
		oc.scaledHeight = resolution.height;

		return resolution;
	}

	public static void scale(Dimension d) {
		int r = findRatio(d);

		d.width /= r;
		d.height /= r;
	}

	private static int findRatio(Dimension d) {
		int r = 1;
		while (match(d.width, r, 320) && match(d.height, r, 240))
			++r;

		return r;
	}

	private static boolean match(int num, int aspectRatio, int max) {
		return num / (aspectRatio + 1) >= max;
	}

}
