package materialcalc.house.godbeom.com.materialcalc.sample.realm.db;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by god on 2018. 7. 12..
 */

public class NotiADTO extends RealmObject {



	private String title;
	private String category; //배송,푸시,댓글 알림
	private RealmList<NotiLinkTextADTO> links;


	public NotiADTO(){
		super();
	}
	public NotiADTO( String category,String title, RealmList<NotiLinkTextADTO> links){
 		this.category = category;
		this.title = title;
		this.links = links;
	}

	public NotiADTO( String category,String title){
		this.category = category;
		this.title = title;
	}



	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public RealmList<NotiLinkTextADTO> getLinks() {
		return links;
	}

	public void setLinks(RealmList<NotiLinkTextADTO> links) {
		this.links = links;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
