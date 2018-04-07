package srp.com.fallrooz.adapter.discretescrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import srp.com.fallrooz.R;
import srp.com.fallrooz.adapter.discretescrollview.transform.DiscreteScrollItemTransformer;
import srp.com.fallrooz.adapter.discretescrollview.util.ScrollListenerAdapter;

/**
 * Created by yarolegovich on 18.02.2017.
 */
@SuppressWarnings("unchecked")
public class DiscreteScrollView extends RecyclerView {

    public static final int NO_POSITION = DiscreteScrollLayoutManager.NO_POSITION;

    private static final int DEFAULT_ORIENTATION = srp.com.fallrooz.adapter.discretescrollview.Orientation.HORIZONTAL.ordinal();

    private DiscreteScrollLayoutManager layoutManager;

    private List<ScrollStateChangeListener> scrollStateChangeListeners;
    private List<OnItemChangedListener> onItemChangedListeners;

    public DiscreteScrollView(Context context) {
        super(context);
        init(null);
    }

    public DiscreteScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DiscreteScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        scrollStateChangeListeners = new ArrayList<>();
        onItemChangedListeners = new ArrayList<>();

        int orientation = DEFAULT_ORIENTATION;
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DiscreteScrollView);
            orientation = ta.getInt(R.styleable.DiscreteScrollView_dsv_orientation, DEFAULT_ORIENTATION);
            ta.recycle();
        }

        layoutManager = new DiscreteScrollLayoutManager(
                getContext(), new ScrollStateListener(),
                srp.com.fallrooz.adapter.discretescrollview.Orientation.values()[orientation]);
        setLayoutManager(layoutManager);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (layout instanceof DiscreteScrollLayoutManager) {
            super.setLayoutManager(layout);
        } else {
            throw new IllegalArgumentException(getContext().getString(R.string.dsv_ex_msg_dont_set_lm));
        }
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {
        boolean isFling = super.fling(velocityX, velocityY);
        if (isFling) {
            layoutManager.onFling(velocityX, velocityY);
        } else {
            layoutManager.returnToCurrentPosition();
        }
        return isFling;
    }

    @Nullable
    public ViewHolder getViewHolder(int position) {
        View view = layoutManager.findViewByPosition(position);
        return view != null ? getChildViewHolder(view) : null;
    }

    /**
     * @return adapter position of the current item or -1 if nothing is selected
     */
    public int getCurrentItem() {
        return layoutManager.getCurrentPosition();
    }

    public void setItemTransformer(DiscreteScrollItemTransformer transformer) {
        layoutManager.setItemTransformer(transformer);
    }

    public void setItemTransitionTimeMillis(@IntRange(from = 10) int millis) {
        layoutManager.setTimeForItemSettle(millis);
    }

    public void setOrientation(srp.com.fallrooz.adapter.discretescrollview.Orientation orientation) {
        layoutManager.setOrientation(orientation);
    }

    public void setOffscreenItems(int items) {
        layoutManager.setOffscreenItems(items);
    }

    public void addScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        scrollStateChangeListeners.add(scrollStateChangeListener);
    }

    public void addScrollListener(@NonNull ScrollListener<?> scrollListener) {
        addScrollStateChangeListener(new ScrollListenerAdapter(scrollListener));
    }

    public void addOnItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        onItemChangedListeners.add(onItemChangedListener);
    }

    public void removeScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        scrollStateChangeListeners.remove(scrollStateChangeListener);
    }

    public void removeScrollListener(@NonNull ScrollListener<?> scrollListener) {
        removeScrollStateChangeListener(new ScrollListenerAdapter<>(scrollListener));
    }

    public void removeItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        onItemChangedListeners.remove(onItemChangedListener);
    }

    private void notifyScrollStart(ViewHolder holder, int current) {
        for (ScrollStateChangeListener listener : scrollStateChangeListeners) {
            listener.onScrollStart(holder, current);
        }
    }

    private void notifyScrollEnd(ViewHolder holder, int current) {
        for (ScrollStateChangeListener listener : scrollStateChangeListeners) {
            listener.onScrollEnd(holder, current);
        }
    }

    private void notifyScroll(float position, ViewHolder currentHolder, ViewHolder newHolder) {
        for (ScrollStateChangeListener listener : scrollStateChangeListeners) {
            listener.onScroll(position, currentHolder, newHolder);
        }
    }

    private void notifyCurrentItemChanged(ViewHolder holder, int current) {
        for (OnItemChangedListener listener : onItemChangedListeners) {
            listener.onCurrentItemChanged(holder, current);
        }
    }

    private void notifyCurrentItemChanged() {
        if (onItemChangedListeners.isEmpty()) {
            return;
        }
        int current = layoutManager.getCurrentPosition();
        ViewHolder currentHolder = getViewHolder(current);
        notifyCurrentItemChanged(currentHolder, current);
    }

    private class ScrollStateListener implements DiscreteScrollLayoutManager.ScrollStateListener {

        @Override
        public void onIsBoundReachedFlagChange(boolean isBoundReached) {
            setOverScrollMode(isBoundReached ? OVER_SCROLL_ALWAYS : OVER_SCROLL_NEVER);
        }

        @Override
        public void onScrollStart() {
            if (scrollStateChangeListeners.isEmpty()) {
                return;
            }
            int current = layoutManager.getCurrentPosition();
            ViewHolder holder = getViewHolder(current);
            if (holder != null) {
                notifyScrollStart(holder, current);
            }
        }

        @Override
        public void onScrollEnd() {
            if (onItemChangedListeners.isEmpty() && scrollStateChangeListeners.isEmpty()) {
                return;
            }
            int current = layoutManager.getCurrentPosition();
            ViewHolder holder = getViewHolder(current);
            if (holder != null) {
                notifyScrollEnd(holder, current);
                notifyCurrentItemChanged(holder, current);
            }
        }

        @Override
        public void onScroll(float currentViewPosition) {
            if (scrollStateChangeListeners.isEmpty()) {
                return;
            }
            int current = getCurrentItem();
            ViewHolder currentHolder = getViewHolder(getCurrentItem());

            int newCurrent = current + (currentViewPosition < 0 ? 1 : -1);
            ViewHolder newCurrentHolder = getViewHolder(newCurrent);

            if (currentHolder != null && newCurrentHolder != null) {
                notifyScroll(currentViewPosition, currentHolder, newCurrentHolder);
            }
        }

        @Override
        public void onCurrentViewFirstLayout() {
            post(new Runnable() {
                @Override
                public void run() {
                    notifyCurrentItemChanged();
                }
            });
        }

        @Override
        public void onDataSetChangeChangedPosition() {
            notifyCurrentItemChanged();
        }
    }

    public interface ScrollStateChangeListener<T extends ViewHolder> {

        void onScrollStart(@NonNull T currentItemHolder, int adapterPosition);

        void onScrollEnd(@NonNull T currentItemHolder, int adapterPosition);

        void onScroll(float scrollPosition, @NonNull T currentHolder, @NonNull T newCurrent);
    }

    public interface ScrollListener<T extends ViewHolder> {

        void onScroll(float scrollPosition, @NonNull T currentHolder, @NonNull T newCurrent);
    }

    public interface OnItemChangedListener<T extends ViewHolder> {
        /*
         * This method will be also triggered when view appears on the screen for the first time.
         * If data set is empty, viewHolder will be null and adapterPosition will be NO_POSITION
         */
        void onCurrentItemChanged(@Nullable T viewHolder, int adapterPosition);
    }
}