import java.util.ArrayList;

public class KeyValue {

	protected Object key;
	protected Object value;
	
	public KeyValue(Object k, Object v) {
		key = k;
		value = v;
	}
	
	public Object getValue() {
		return value;
	}
	
	public Object getKey() {
		return key;
	}	
	
	public void setKey(Object k) {
		key = k;
	}
	
	public void setValue(Object v) {
		value = v;
	}
	
	public String toString() {
		return key + ":" + value;
	}
}
