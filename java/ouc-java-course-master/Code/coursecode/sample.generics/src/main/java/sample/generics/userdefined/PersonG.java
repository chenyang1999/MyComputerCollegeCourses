package sample.generics.userdefined;

/**
 * 自定义泛型类
 * 
 * @author xiaodong
 *
 * @param <T>
 */
public class PersonG<T> { // 泛型参数为T
	private int id;
	private String name;
	private T secrecy;

	public PersonG(int id) {
		this.setId(id);
	}

	public void setSecrecy(T secrecy) {
		this.secrecy = secrecy;
	}

	public T getSecrecy() {
		return secrecy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
