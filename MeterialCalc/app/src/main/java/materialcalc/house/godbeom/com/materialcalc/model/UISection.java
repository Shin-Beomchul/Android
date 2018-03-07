package materialcalc.house.godbeom.com.materialcalc.model;

import java.util.ArrayList;

public class UISection {

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

    public void reset() {
        mHeader = null;
        mFooter = null;
        mItems.clear();
    }
}