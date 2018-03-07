package materialcalc.house.godbeom.com.materialcalc.model;

import java.util.HashMap;

/**
 * Created by Administrator on 2018-03-06.
 */

public class UIItem {


	private static final String KEY_ITEM = "item";
	private static final String KEY_VIEWTYPE = "viewType";
	private int layoutId ;


	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	public int getLayoutId() {
		return layoutId;
	}

	HashMap<String, Object> objects = new HashMap<>();

	public void putData(Object value) {
		put(KEY_ITEM, value);
	}

	public Object getData() {
		return get(KEY_ITEM);
	}

	public void putViewType(int value) {
		put(KEY_VIEWTYPE, (Integer) value);
	}

	public int getViewType(){
		return get(KEY_VIEWTYPE) != null ? (Integer) get(KEY_VIEWTYPE) : -1;
	}

	public void put(String key, Object value) {
		objects.put(key, value);
	}

	public Object get(String key) {
		return objects.containsKey(key) ? objects.get(key) : null;
	}

}
