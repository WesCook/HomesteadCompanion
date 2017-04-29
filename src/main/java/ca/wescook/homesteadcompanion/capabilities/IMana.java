package ca.wescook.homesteadcompanion.capabilities;

// Interface describes the methods the Implementations should understand
public interface IMana {
	public void consume(float points);
	public void fill(float points);
	public void set(float points);
	public float getMana();
}
