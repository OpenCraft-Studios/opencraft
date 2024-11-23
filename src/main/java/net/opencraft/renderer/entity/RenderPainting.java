
package net.opencraft.renderer.entity;

import static net.opencraft.OpenCraft.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import net.opencraft.EnumArt;
import net.opencraft.entity.EntityPainting;
import net.opencraft.renderer.Tessellator;
import net.opencraft.util.Mth;

public class RenderPainting extends Render<EntityPainting> {

	private static final int FACING_NORTH = 0,
							 FACING_EAST  = 1,
							 FACING_SOUTH = 2,
							 FACING_WEST  = 3;
	
	private Random random;

	public RenderPainting() {
		this.random = new Random();
	}

	public void doRender(EntityPainting entityLiving, double xCoord, double sqrt_double, double yCoord, float nya1,
			float nya2) {
		random.setSeed(187L);
		glPushMatrix();
		glTranslatef((float) xCoord, (float) sqrt_double, (float) yCoord);
		glRotatef(nya1, 0.0f, 1.0f, 0.0f);
		glEnable(32826);
		this.loadTexture("/assets/art/kz.png");
		EnumArt art = entityLiving.art;
		float n = 0.0625f;
		glScalef(n, n, n);
		this.renderPainting(entityLiving, art.sizeX, art.sizeY, art.offsetX, art.offsetY);
		glDisable(32826);
		glPopMatrix();
	}

	private void renderPainting(EntityPainting painting, int width, int height, int offsetX, int offsetY) {
		float[] x = new float[2];
		float[] y = new float[2];
		float[] z = new float[2];

		float[] u = new float[8];
		float[] v = new float[8];

		float halfWidth = ((float) -width) * 0.5f;
		float halfHeight = ((float) -height) * 0.5f;

		// Loop through each 16x16 segment of the painting
		for (int i = 0; i < width / 16; ++i) {
			for (int j = 0; j < height / 16; ++j) {
				// X coordinates
				x[0] = halfWidth + (i + 1) * 16;
				x[1] = halfWidth + i * 16;

				// Y coordinates
				y[0] = halfHeight + j * 16;
				y[1] = halfHeight + (j + 1) * 16;

				// Z coordinates
				z[0] = -0.5f;
				z[1] = 0.5f;

				// Set up the painting's position in the world
				updatePaintingPosition(painting, (x[0] + x[1]) / 2.0f, (y[1] + y[0]) / 2.0f);

				// UV texture coordinates for texture mapping
				// U coordinates
				u[0] = (offsetX + width - (i + 1) * 16) / 256.0f;
				u[1] = (offsetX + width - i * 16) / 256.0f;
				u[2] = 0.75f;
				u[3] = 0.8125f;
				u[4] = 0.75f;
				u[5] = 0.8125f;
				u[6] = 0.7519531f;
				u[7] = 0.7519531f;

				// V coordinates
				v[0] = (offsetY + height - j * 16) / 256.0f;
				v[1] = (offsetY + height - (j + 1) * 16) / 256.0f;
				v[2] = 0.0f;
				v[3] = 0.0625f;
				v[4] = 0.001953125f;
				v[5] = 0.001953125f;
				v[6] = 0.0f;
				v[7] = 0.0625f;

				// Start drawing the quads for the painting
				Tessellator tessellator = Tessellator.instance;
				tessellator.beginQuads();
				{
					// Draw the back face of the quad
					tessellator.normal(0.0f, 0.0f, -1.0f);
					tessellator.vertexUV(x[0], y[0], z[0], u[0], v[0]);
					tessellator.vertexUV(x[1], y[0], z[0], u[1], v[0]);
					tessellator.vertexUV(x[1], y[1], z[0], u[1], v[1]);
					tessellator.vertexUV(x[0], y[1], z[0], u[0], v[1]);

					// Draw the front face of the quad
					tessellator.normal(0.0f, 0.0f, 1.0f);
					tessellator.vertexUV(x[0], y[1], z[1], u[2], v[2]);
					tessellator.vertexUV(x[1], y[1], z[1], u[3], v[2]);
					tessellator.vertexUV(x[1], y[0], z[1], u[3], v[3]);
					tessellator.vertexUV(x[0], y[0], z[1], u[2], v[3]);

					// Draw the top face of the quad
					tessellator.normal(0.0f, -1.0f, 0.0f);
					tessellator.vertexUV(x[0], y[1], z[0], u[4], v[4]);
					tessellator.vertexUV(x[1], y[1], z[0], u[5], v[4]);
					tessellator.vertexUV(x[1], y[1], z[1], u[5], v[5]);
					tessellator.vertexUV(x[0], y[1], z[1], u[4], v[5]);

					// Draw the bottom face of the quad
					tessellator.normal(0.0f, 1.0f, 0.0f);
					tessellator.vertexUV(x[0], y[0], z[1], u[4], v[4]);
					tessellator.vertexUV(x[1], y[0], z[1], u[5], v[4]);
					tessellator.vertexUV(x[1], y[0], z[0], u[5], v[5]);
					tessellator.vertexUV(x[0], y[0], z[0], u[4], v[5]);

					// Draw the left face of the quad
					tessellator.normal(-1.0f, 0.0f, 0.0f);
					tessellator.vertexUV(x[0], y[1], z[1], u[6], v[6]);
					tessellator.vertexUV(x[0], y[0], z[1], u[6], v[7]);
					tessellator.vertexUV(x[0], y[0], z[0], u[7], v[7]);
					tessellator.vertexUV(x[0], y[1], z[0], u[7], v[6]);

					// Draw the right face of the quad
					tessellator.normal(1.0f, 0.0f, 0.0f);
					tessellator.vertexUV(x[1], y[1], z[0], u[6], v[6]);
					tessellator.vertexUV(x[1], y[0], z[0], u[6], v[7]);
					tessellator.vertexUV(x[1], y[0], z[1], u[7], v[7]);
					tessellator.vertexUV(x[1], y[1], z[1], u[7], v[6]);
				}
				tessellator.render();
			}
		}
	}

	private void updatePaintingPosition(EntityPainting painting, float offsetX, float offsetY) {
	    int baseX = Mth.floor_double(painting.x);
	    int baseY = Mth.floor_double(painting.y + offsetY / 16.0f);
	    int baseZ = Mth.floor_double(painting.z);

	    // Adjust the position based on the direction the painting is facing
	    switch (painting.direction) {
	        case FACING_NORTH: // North-facing
	            baseX = Mth.floor_double(painting.x + offsetX / 16.0f);
	            break;

	        case FACING_EAST: // East-facing
	            baseZ = Mth.floor_double(painting.z - offsetX / 16.0f);
	            break;

	        case FACING_SOUTH: // South-facing
	            baseX = Mth.floor_double(painting.x - offsetX / 16.0f);
	            break;

	        case FACING_WEST: // West-facing
	            baseZ = Mth.floor_double(painting.z + offsetX / 16.0f);
	            break;
	    }

	    // Calculate the light brightness at the painting's location
	    float lightBrightness = oc.world.getLightBrightness(baseX, baseY, baseZ);
	    
	    // Set the color for rendering based on the light brightness
	    glColor3f(lightBrightness, lightBrightness, lightBrightness);
	}

}
