package materialcalc.house.godbeom.com.materialcalc.sample.realm.db;

import io.realm.RealmObject;

/**
 * Created by god on 2018. 7. 12..
 */

public class NotiLinkTextADTO extends RealmObject {

	private String text;
	private String link;

	public NotiLinkTextADTO(){
		super();
	}
	public NotiLinkTextADTO(String text, String link){

		this.text = text;
		this.link = link;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
