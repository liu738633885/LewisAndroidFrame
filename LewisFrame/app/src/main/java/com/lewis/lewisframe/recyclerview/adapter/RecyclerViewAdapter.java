package com.lewis.lewisframe.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.modle.TestModle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<TestModle> mData = new ArrayList<TestModle>();


    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //点击事件接口
    private OnRecyclerViewListener onRecyclerViewListener;

    public static interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    /**
     * 最原始的添加数据
     *
     * @param addList
     */
    public void addData(List<TestModle> addList) {
        mData.addAll(addList);
        notifyItemRangeChanged(getItemCount() - 1, addList.size());
    }

    /**
     * 在指定位置添加数据
     *
     * @param addList
     * @param position
     */
    public void addData(List<TestModle> addList, int position) {
        if (position > getItemCount()) {
            position = getItemCount();
        }
        notifyItemRangeInserted(position, addList.size());//添加了动画
        mData.addAll(position, addList);
        notifyItemRangeChanged(position, getItemCount());//为了刷新数据的位置
    }

    /**
     * 删除数据
     *
     * @param position
     */
    public void deletData(int position) {
        notifyItemRemoved(position);
        mData.remove(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public int getItemViewType(int position) {

        return mData.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TestModle.TYPE_1) {
            return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_test1, parent, false));
        } else {
            return new MyViewHolder1(mLayoutInflater.inflate(R.layout.item_test2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder1) {
            ((MyViewHolder1) holder).tv_title.setText(mData.get(position).getTitle() + "position" + position);
        } else {
            ((MyViewHolder2) holder).tv_title.setText(mData.get(position).getTitle() + "position" + position);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView tv_title;
        View itemView;

        MyViewHolder1(View view) {
            super(view);
            itemView = view;
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onRecyclerViewListener) {
                return onRecyclerViewListener.onItemLongClick(this.getAdapterPosition());
            }
            deletData(getAdapterPosition());
            return false;
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView tv_title;
        View itemView;

        MyViewHolder2(View view) {
            super(view);
            itemView = view;
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onRecyclerViewListener) {
                return onRecyclerViewListener.onItemLongClick(this.getAdapterPosition());
            }
            deletData(getAdapterPosition());
            return false;
        }
    }
    //notifyItemChanged(int position)
    //position数据发生了改变，那调用这个方法，就会回调对应position的onBindViewHolder()
    // 方法了，当然，因为ViewHolder是复用的，所以如果position在当前屏幕以外，也就不会回调了，因为没有意义，下次position滚动会当前屏幕以内的时候同样会调用onBindViewHolder()
    // 方法刷新数据了。其他的方法也是同样的道理。

    //notifyItemRangeChanged(int positionStart, int itemCount)
    //顾名思义，可以刷新从positionStart开始itemCount数量的item了（这里的刷新指回调onBindViewHolder()方法）。

    //notifyItemInserted(int position)，
    //这个方法是在第position位置被插入了一条数据的时候可以使用这个方法刷新，注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。

    //notifyItemMoved(int fromPosition, int toPosition)
    //这个方法是从fromPosition移动到toPosition为止的时候可以使用这个方法刷新

    //notifyItemRangeInserted(int positionStart, int itemCount)//显然是批量添加。

    //notifyItemRemoved(int position)//第position个被删除的时候刷新，同样会有动画。
    //notifyItemRangeRemoved(int positionStart, int itemCount)//批量删除。
}
