package materialcalc.house.godbeom.com.materialcalc.sample.utill.decorator;


import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jongsic.kim
 */
public class GridHorizontalSpacingDecoration extends RecyclerView.ItemDecoration {

    private int mOffsetPx;

    public GridHorizontalSpacingDecoration(int offsetPx) {
        mOffsetPx = offsetPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if( parent.getLayoutManager() instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
            int childPosition = parent.getChildAdapterPosition(view);

            if ( childPosition%spanCount == 0 ) {
                outRect.left = mOffsetPx;
            } else if( childPosition%spanCount == spanCount-1 ) {
                outRect.right = mOffsetPx;
            }

        }
    }


}
