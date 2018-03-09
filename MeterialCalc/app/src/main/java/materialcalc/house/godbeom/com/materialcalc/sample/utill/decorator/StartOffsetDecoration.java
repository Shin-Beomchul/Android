package materialcalc.house.godbeom.com.materialcalc.sample.utill.decorator;


import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView - 시작 영역에 Dummy 영역 지정을 위한 decoration <br><br>
 *
 * Created by jongsic.kim
 */
public class StartOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mOffsetPx;

    public StartOffsetDecoration(int offsetPx) {
        mOffsetPx = offsetPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int spanCount = 1;
        if( parent.getLayoutManager() instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        }

        if (parent.getChildAdapterPosition(view) < spanCount) {
            int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                outRect.left = mOffsetPx;
            } else if (orientation == LinearLayoutManager.VERTICAL) {
                outRect.top = mOffsetPx;
            }
        }
    }


}
