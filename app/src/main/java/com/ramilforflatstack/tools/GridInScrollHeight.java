package com.ramilforflatstack.tools;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by Ramil on 15.06.2015.
 */
public class GridInScrollHeight {

    public static void setGridViewHeightBasedOnChildren(GridView gridView, int itemsInRow, int spacing) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        View item = listAdapter.getView(0, null, gridView);
        if(item != null) {
            item.measure(0, 0);
            int rowCount = (int) Math.ceil(1d * listAdapter.getCount() / itemsInRow);
            int totalHeight = rowCount * item.getMeasuredHeight() + (rowCount) * spacing;

            Log.e("mytag", "totalHeight = " + totalHeight);
            ViewGroup.LayoutParams params = gridView.getLayoutParams();
            params.height = totalHeight;
            gridView.setLayoutParams(params);
            gridView.requestLayout();
        }
    }
}
