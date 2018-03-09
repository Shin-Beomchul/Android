package materialcalc.house.godbeom.com.materialcalc.sample.utill.decorator;


import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView - 마지막 영역에 dummy 영역 추가를 위한 decorator <br><br>
 *
 * Created by jongsic.kim
 */
public class EndOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mOffsetPx;

    public EndOffsetDecoration(int offsetPx) {
        mOffsetPx = offsetPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemCount = state.getItemCount();
        if (parent.getChildAdapterPosition(view) == itemCount - 1) {
            int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
            if (orientation == LinearLayoutManager.HORIZONTAL) {
                outRect.right = mOffsetPx;
            } else {
                outRect.bottom = mOffsetPx;
            }
        }
    }


}
