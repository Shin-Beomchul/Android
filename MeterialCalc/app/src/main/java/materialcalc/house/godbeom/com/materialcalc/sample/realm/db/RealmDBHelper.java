package materialcalc.house.godbeom.com.materialcalc.sample.realm.db;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by god on 2018. 7. 12..
 */

public class RealmDBHelper  {


	//노티피케이션 데이터 Insert
	public void addNoti(NotiADTO notiADTO) {
		Realm.getDefaultInstance()
				.executeTransaction(new Realm.Transaction() {
					@Override
					public void execute(Realm realm) {
						NotiADTO noti = realm.createObject(NotiADTO.class);
						noti.setTitle(notiADTO.getTitle());
						noti.setCategory(notiADTO.getCategory());

						for (NotiLinkTextADTO text : notiADTO.getLinks()) {
							NotiLinkTextADTO notiTextR = realm.createObject(NotiLinkTextADTO.class);
							notiTextR.setText(text.getText());
							notiTextR.setLink(text.getLink());
							noti.getLinks().add(notiTextR);
						}
					}
				});
	}


	//All
	public RealmResults<NotiADTO> getAllNotis() {
		return Realm
				.getDefaultInstance()
				.where(NotiADTO.class)
				.findAll();
	}

	//키워드 검색
	public RealmResults<NotiADTO> getNotiByTitle(String title) {
		return Realm
				.getDefaultInstance()
				.where(NotiADTO.class)
				.like("title", "*" + title + "*")
				.findAll();
	}

	//알림 타입별
	public RealmResults<NotiADTO> getNitiByCategory(String category) {
		return Realm
				.getDefaultInstance()
				.where(NotiADTO.class)
				.equalTo("category", category)
				.findAll();
	}


	//카테고리
	public String[] getCategorys() {
		RealmResults<NotiADTO> results = Realm
				.getDefaultInstance()
				.where(NotiADTO.class)
				.distinct("category");

		String[] categorys = new String[results.size()];
		int i = 0;
		for (NotiADTO d : results) {
			categorys[i] = d.getCategory();
			i++;
		}
		return categorys;

	}


	//타이틀에 맞으면 삭제
	public void deleteNotiByTitle(String title) {
		Realm.getDefaultInstance()
				.executeTransaction(realm ->
						realm
								.where(NotiADTO.class)
								.equalTo("title", title)
								.findAll());
	}



}
