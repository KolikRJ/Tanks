package usb.connected.device;

import java.util.ArrayList;

public class ConnectedDevice {
	private final static String REGQUERY_UTIL = "reg query ";
	private final static String REGSTR_TOKEN = "REG_SZ";

	public static ArrayList<String> getConnectedDevices() {

		ArrayList<String> listDev = new ArrayList<String>();

		for (int i = 0; i < 30; i++) {

			String path = REGQUERY_UTIL + "\"HKLM\\SYSTEM\\CurrentControlSet\\services\\USBSTOR\\Enum\"" + " /v " + i;
			try {
				Process process = Runtime.getRuntime().exec(path);
				StreamReader reader = new StreamReader(process.getInputStream());

				reader.start();
				process.waitFor();
				reader.join();

				String result = reader.getResult();
				int p = result.indexOf(REGSTR_TOKEN);
				if (p != -1) {
					listDev.add(result.substring(p + REGSTR_TOKEN.length() + 26).trim());

					String pathPort = REGQUERY_UTIL + "\"HKLM\\SYSTEM\\CurrentControlSet\\Enum\\" + result.substring(p + REGSTR_TOKEN.length()).trim() + "\"" + " /v LocationInformation";

					process = Runtime.getRuntime().exec(pathPort);
					reader = new StreamReader(process.getInputStream());

					reader.start();
					process.waitFor();
					reader.join();

					String result2 = reader.getResult();
					int p2 = result2.indexOf(REGSTR_TOKEN);
					if (p2 != -1)
						listDev.add(result2.substring(p2 + REGSTR_TOKEN.length()).trim());

					String pathPrefixId = REGQUERY_UTIL + "\"HKLM\\SYSTEM\\CurrentControlSet\\Enum\\" + result.substring(p + REGSTR_TOKEN.length()).trim() + "\"" + " /v ParentIdPrefix";

					process = Runtime.getRuntime().exec(pathPrefixId);
					reader = new StreamReader(process.getInputStream());

					reader.start();
					process.waitFor();
					reader.join();

					String result3 = reader.getResult();
					int p3 = result3.indexOf(REGSTR_TOKEN);
					if (p3 != -1)
						listDev.add(result3.substring(p3 + REGSTR_TOKEN.length()).trim());
					else
						listDev.add("null");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listDev;
	}

}
