package usb.connected.device;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ConnectedPort {

	private final static String REGQUERY_UTIL = "reg query ";

	private String name = "";
	private String prefixId = "";
	private String port = "";
	private String chars = "";
	private String size = "";

	private boolean isConnected = false;

	public ConnectedPort(String portDev) {
		this.port = portDev;
	}

	public String getSizeDev() {

		File file = new File("/" + chars + ":");
		float total = ((float) file.getTotalSpace() / 1073741824);
		float free = ((float) file.getFreeSpace() / 1073741824);
		float engaged = total - free;

		return " Занято: " + String.format("%.2f", engaged) + " Gb из: "
				+ String.format("%.1f", total) + " Gb";
	}

	public void getConnectedPort() {

		ArrayList<String> arrPortDev = ConnectedDevice.getConnectedDevices();

		if (arrPortDev.size() != 0)
			for (int i = 1; i < arrPortDev.size(); i += 3) {
				if (arrPortDev.get(i).equals(port)) {
					isConnected = true;
					name = arrPortDev.get(i - 1);
					prefixId = arrPortDev.get(i + 1);
					getChar();
					size = getSizeDev();
					break;
				} else {
					isConnected = false;
					chars = "";
					size = "";
				}
			}
		else {
			isConnected = false;
			chars = "";
			size = "";
		}

	}

	private void getChar() {
		char disk = 'A';

		for (int i = 0; i < 30; i++) {

			String path = REGQUERY_UTIL + "\"HKLM\\SYSTEM\\MountedDevices\""
					+ " /v \\DosDevices\\" + disk + ":";
			try {

				Process process = Runtime.getRuntime().exec(path);
				StreamReader reader = new StreamReader(process.getInputStream());

				reader.start();
				process.waitFor();
				reader.join();

				String result = reader.getResult();

				int p = result.indexOf("REG_BINARY");
				if (p != -1) {
					result = result.substring(p + "REG_BINARY".length()).trim();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					for (int j = 0; j < result.length(); j += 4) {
						String str = result.substring(j, j + 2);
						int byteVal = Integer.parseInt(str, 16);
						baos.write(byteVal);
					}
					result = new String(baos.toByteArray(),
							Charset.forName("UTF-8"));
					
					int sizeStr = result.indexOf(name);
					int sizeStrId = result.indexOf(prefixId);
					if (sizeStr != -1 || sizeStrId != -1) {
						chars = "" + disk;
						break;
					}
				}

				disk++;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChars() {
		return chars;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public String toString() {
		String string = isConnected ? "Подключен" : "Не подключен";
		String disk = isConnected ? "<br>Диск: " : "";
		return "<html>" + string  + disk + chars + "<br>" + size + "<html>";
	}

}
