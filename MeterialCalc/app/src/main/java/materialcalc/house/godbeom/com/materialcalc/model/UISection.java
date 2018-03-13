package materialcalc.house.godbeom.com.materialcalc.model;

import java.util.ArrayList;

public class UISection {

    int sectionId;
    UIItem mHeader;
	UIItem mFooter;
    ArrayList<UIItem> mItems;

    public UISection() {
        mItems = new ArrayList<UIItem>();
    }

    public UIItem getHeader() {
        return mHeader;
    }

    public void setHeader(UIItem item) {
        mHeader = item;
    }

    public UIItem getFooter() {
        return mFooter;
    }

    public void setFooter(UIItem item) {
        mFooter = item;
    }

    public boolean hasHeader() {
        return mHeader != null;
    }

    public boolean hasFooter() {
        return mFooter != null;
    }

    public void addItem(UIItem item) {
        mItems.add(item);
    }

    public void addItems(ArrayList<UIItem> items) {
        mItems.addAll(items);
    }

    public ArrayList<UIItem> getItems() {
        return mItems;
    }


    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }


    public int getSectionId() {
        return sectionId;
    }

    public void reset() {
        mHeader = null;
        mFooter = null;
        mItems.clear();
    }

    public int getSectionItemSize(){
        if(mItems.isEmpty())
            return 0;

        int sectionTotalSize = mItems.size();

        if(hasFooter()){
            sectionTotalSize++;
        }
        if(hasHeader()){
            sectionTotalSize++;
        }

        return sectionTotalSize;

    }
}