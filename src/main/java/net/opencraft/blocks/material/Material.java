package net.opencraft.blocks.material;

public enum Material {

	AIR(Material.TRANSPARENT),
	GROUND,
	WOOD,
	ROCK,
	METAL,
	WATER(Material.LIQUID),
	LAVA(Material.LIQUID),
	LEAVES,
	PLANTS(Material.LOGIC),
	SPONGE,
	CLOTH,
	FIRE(Material.TRANSPARENT),
	SAND,
	REDSTONE(Material.LOGIC),
	GLASS,
	TNT;
	
	private static final byte TRANSPARENT = 0b001,
							  LIQUID      = 0b100,
							  LOGIC       = 0b000;
	
	private boolean liquid, solid, grass;
	
	Material(boolean liquid, boolean solid, boolean grass) {
		this.liquid = liquid;
		this.solid = solid;
		this.grass = grass;
	}
	
	Material(byte type) {
		this((type & 0b100) != 0, (type & 0b010) != 0, (type & 0b001) != 0);
	}
	
	Material() {
		this(false, true, true);
	}
	
	public boolean isLiquid() {
		return liquid;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isBlockGrass() {
		return grass;
	}

}
