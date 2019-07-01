package sample.advance.door;

public class AlarmDoor extends Door implements Alarm {

	public void alarm(int code) {

		switch (code) {
		case 1:
			System.out.println("语音播报：有人按门铃。");
			break;

		case 2:
			System.out.println("警铃报警：有人非法闯入！");
			break;

		default:
			System.out.println("语音播报：注意安全。");
		}
	}

	@Override
	void open() {
		System.out.println("正在开门...");
	}

	@Override
	void close() {
		System.out.println("正在关门...");
	}

}
