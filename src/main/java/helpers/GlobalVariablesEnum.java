package helpers;

public enum GlobalVariablesEnum {
	state, air_data, setpoint, params, fault_status, airspeed_sample_delayed, i2c_trans, radio_control, gps,
	guidance_h, autopilot, electrical, motor_mixing, sys_time, gh_ref,control_status, imu_bebop;
	
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