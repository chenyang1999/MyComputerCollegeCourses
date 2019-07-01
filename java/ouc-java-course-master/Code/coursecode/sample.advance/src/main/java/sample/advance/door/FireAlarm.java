package sample.advance.door;

public class FireAlarm implements Alarm {

	public void alarm(int code) {
		switch (code) {
		case 1000:
			System.out.println("警铃报警：哇呜哇呜...");
		default:
			System.out.println("语音播报：有火警，尽快由消防通道撤离到安全区域，不要使用电梯！");
		}
	}
	
	public static void main(String[] args) {
		new FireAlarm().alarm(2000);
	}
}
