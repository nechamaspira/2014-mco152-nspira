package spira.vendingmachine;

public class DuplicateDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateDataException(){
		super("exist already");
	}
}
