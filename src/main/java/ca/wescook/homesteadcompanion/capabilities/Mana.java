package ca.wescook.homesteadcompanion.capabilities;

// Default implementation, contains logic for each method defined in the Interface
public class Mana implements IMana {
	private float mana = 250.0F;

	@Override
	public void consume(float points) {
		mana = Math.max(mana - points, 0F);
	}

	@Override
	public void fill(float points) {
		mana = Math.min(mana + points, 100F);
	}

	@Override
	public void set(float points) {
		mana = points;
	}

	@Override
	public float getMana() {
		return mana;
	}
}
