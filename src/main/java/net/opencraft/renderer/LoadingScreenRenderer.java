
package net.opencraft.renderer;

import static net.opencraft.OpenCraft.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import net.opencraft.renderer.gui.IProgressListener;

public class LoadingScreenRenderer implements IProgressListener {

	private static final long INTERVAL = 20L;
	private String loadingMessage;
	private String currentlyDisplayedText;
	private long startTime;
	private boolean stageInProgress;

	public LoadingScreenRenderer() {
		this.loadingMessage = "";
		this.currentlyDisplayedText = "";
		this.startTime = System.currentTimeMillis();
		this.stageInProgress = false;
	}

	public void printText(final String string) {
		this.stageInProgress = false;
		this.setLoadingProgress(string);
	}

	public void setStage(final String string) {
		this.stageInProgress = true;
		this.setLoadingProgress(this.currentlyDisplayedText);
	}

	public void setLoadingProgress(final String string) {
		if (!oc.running)
			return;
		this.currentlyDisplayedText = string;

		// TODO: convert to own method in GUI class
		glClear(GL_DEPTH_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0, oc.scaledWidth, oc.scaledHeight, 0.0, 100.0, 300.0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0f, 0.0f, -200.0f);
	}

	public void setLoadingMessage(final String string) {
		if (oc.running) {
			this.startTime = 0L;
			this.loadingMessage = string;
			this.setProgress(-1);
			this.startTime = 0L;
			return;
		}
		
		if (stageInProgress)
			return;
		
		throw new IllegalStateException();
	}

	public void setProgress(final int integer) {
		if (!oc.running)
			return;

		long now = System.currentTimeMillis();
		if (now - startTime < INTERVAL)
			return;

		this.startTime = now;

		glClear(GL_DEPTH_BUFFER_BIT);

		// TODO: convert to own method in GUI class
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		{
			glOrtho(0.0, oc.scaledWidth, oc.scaledHeight, 0.0, 100.0, 300.0);
		}
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glTranslatef(0.0f, 0.0f, -200.0f);
		glClear(16640);
		final Tessellator t = Tessellator.instance;
		glBindTexture(3553, oc.renderer.loadTexture("/assets/dirt.png"));
		final float n = 32.0f;
		t.beginQuads();
		t.color(0x404040);
		t.vertexUV(0.0, oc.scaledHeight, 0.0, 0.0, oc.scaledHeight / n);
		t.vertexUV(oc.scaledWidth, oc.scaledHeight, 0.0, oc.scaledWidth / n, oc.scaledHeight / n);
		t.vertexUV(oc.scaledWidth, 0.0, 0.0, oc.scaledWidth / n, 0.0);
		t.vertexUV(0.0, 0.0, 0.0, 0.0, 0.0);
		t.render();
		if (integer >= 0) {
			final int n2 = 100;
			final int n3 = 2;
			final int n4 = oc.scaledWidth / 2 - n2 / 2;
			final int n5 = oc.scaledHeight / 2 + 16;
			glDisable(3553);
			t.beginQuads();
			t.color(8421504);
			t.vertex(n4, n5, 0.0);
			t.vertex(n4, n5 + n3, 0.0);
			t.vertex(n4 + n2, n5 + n3, 0.0);
			t.vertex(n4 + n2, n5, 0.0);
			t.color(8454016);
			t.vertex(n4, n5, 0.0);
			t.vertex(n4, n5 + n3, 0.0);
			t.vertex(n4 + integer, n5 + n3, 0.0);
			t.vertex(n4 + integer, n5, 0.0);
			t.render();
			glEnable(3553);
		}
		oc.font.drawShadow(this.currentlyDisplayedText, (oc.scaledWidth - oc.font.width(this.currentlyDisplayedText)) / 2,
				oc.scaledHeight / 2 - 4 - 16, 0xFFFFFF);
		oc.font.drawShadow(this.loadingMessage, (oc.scaledWidth - oc.font.width(this.loadingMessage)) / 2,
				oc.scaledHeight / 2 - 4 + 8, 0xFFFFFF);
		glfwSwapBuffers(oc.window);
		try {
			Thread.yield();
		} catch (Exception ex) {
		}

	}

}
