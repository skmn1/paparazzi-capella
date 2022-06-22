
// class use to define if the statement is a function 
			// Type = 1 : functions without parameters & multiples arguments : f(); ou  f(x,y,...);
			// Type = 2 functions with 1 parameter with ou without pointer : f(x); ou f(&x);
			// Type = 3 functions with multiples arguments including at least 1 pointer : f(&x,y,...);
			// Type = 4 functions called for a temporary variable _1 = f(..) ;
public class StatFunction {
	
	protected boolean Isstatfunction ;
	protected Integer FunctionType;

	public StatFunction(Boolean Isf, Integer Ftype) {
		this.FunctionType = Ftype;
		this.Isstatfunction = Isf;
	}

	public boolean getIsstatfunction() {
		return Isstatfunction;
	}

	public void setIsstatfunction(boolean isstatfunction) {
		Isstatfunction = isstatfunction;
	}
	public void setstatfunction(boolean isstatfunction, int FType) {
		Isstatfunction = isstatfunction;
		this.
	}

	public Integer getFunctionType() {
		return FunctionType;
	}

	public void setFunctionType(Integer functionType) {
		FunctionType = functionType;
	}
}
