package srp.com.fallrooz.adapter.discretescrollview.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import srp.com.fallrooz.adapter.discretescrollview.DiscreteScrollView;


/**
 * Created by yarolegovich on 16.03.2017.
 */
public class ScrollListenerAdapter<T extends RecyclerView.ViewHolder> implements DiscreteScrollView.ScrollStateChangeListener<T> {

    private DiscreteScrollView.ScrollListener<T> adaptee;

    public ScrollListenerAdapter(@NonNull DiscreteScrollView.ScrollListener<T> adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void onScrollStart(@NonNull T currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScrollEnd(@NonNull T currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition, @NonNull T currentHolder, @NonNull T newCurrentHolder) {
        adaptee.onScroll(scrollPosition, currentHolder, newCurrentHolder);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScrollListenerAdapter) {
            return adaptee.equals(((ScrollListenerAdapter) obj).adaptee);
        } else {
            return super.equals(obj);
        }
    }
}
