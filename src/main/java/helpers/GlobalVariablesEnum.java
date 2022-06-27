package helpers;

public enum GlobalVariablesEnum {
	state, air_data, setpoint, msg, params, fault_status, airspeed_sample_delayed, i2c_trans, radio_control, gps,
	guidance_h, autopilot, electrical,motor_mixing, sys_time,;
	//"baro_health_counter.5_3, prescaler.8_16"
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