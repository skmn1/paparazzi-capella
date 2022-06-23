package helpers;

public enum GlobalVariablesEnum {
	state, setpoint; 
	
	public static boolean testenumb (String str) {
		boolean flag = false;
		for (GlobalVariablesEnum Gbv : GlobalVariablesEnum.values()) {
			if (str.contains(Gbv +".")) {
				flag = true;
			}
		}
		return flag;
	}
	public String  testenums (String str) {
		for (GlobalVariablesEnum Gbv : GlobalVariablesEnum.values()) {
			if (str.contains(Gbv +".")) {
				str = Gbv + ".";
			}
		}
		return str;
	}
}