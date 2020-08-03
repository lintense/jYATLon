package jyatlon.core;

public class BlockBuildingError extends Error {

	private static final long serialVersionUID = 1L;
	public final int pos;

	public BlockBuildingError(String message, int pos) {
		super(message);
		this.pos = pos;
	}
	
}
