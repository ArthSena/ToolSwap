package xyz.artsna.toolswap.core.command;

public class Arguments {

	private final String[] args;
	private int i = 1;
	
	public Arguments(String[] args) {
		this.args = args;
	}
	
	/**
	 * Returns the length of the arguments
	 */
	public int length() {
		return args.length;
	}
	
	/**
	 * Save the index value
	 * 
	 * @param index Value to be kept
	 */
	public Arguments i(int index) {
		return index(index);
	}
	
	/**
	 * Save the index value
	 * 
	 * @param index Value to be kept
	 */
	public Arguments index(int index) {
		this.i = index;
		return this;
	}
	
	/**
	 * Returns true if the indexed value is the same as the passed argument
	 * 
	 * @param argument Argument to check
	 */
	public boolean equalsIgnoreCase(String argument) {
		String value = value();
		if(value == null)
			return false;
		
		if(value.equalsIgnoreCase(argument)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the value of the argument at a stored index
	 */
	public String value() {
		if(args.length <= (i-1))
			return null;
		
		return args[i-1];
	}
	
	/**
	 * Returns the value of the argument at a given index
	 * 
	 * @param index Index of value
	 */
	public String value(int index) {
		if(args.length <= (index-1))
			return null;
		
		return args[index-1];
	}
	
	public String[] values(int startIndex) {
		if(startIndex > args.length)
			throw new IndexOutOfBoundsException("startIndex can't be bigger than args length");
		
		startIndex -=1;
		
		String[] v = new String[args.length - startIndex];

		int argIndex = startIndex;
		for(int i = 0; i < (args.length - startIndex); i++) {
			
			
			v[i] = args[argIndex];
			argIndex++;
		}
		
		return v;
	}
	
	/**
	 * Returns true if the indexed value is the same as the passed argument (case insensitive)
	 * 
	 * @param argument Argument to check
	 */
	public boolean equals(Object argument) {
		if(!(argument instanceof String))
			throw new IllegalArgumentException("you can only use arguments of type 'String'");
		
		return equalsIgnoreCase(argument.toString());
	}
	
}
