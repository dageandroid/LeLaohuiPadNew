package dq.lelaohui.com.lelaohuipad.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dq.lelaohui.com.lelaohuipad.R;

/**
 * Created by ZTF on 2016/11/20.
 */

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.ViewHolder> implements  View.OnClickListener{
    ArrayList<String> foodTypeList;
    Context context;
    private OnRecyclerViewItemClickListener mOnItemClickListener=null;

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(view,(String)view.getTag());
        }
    }

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public static interface  OnRecyclerViewItemClickListener{
        void onItemClick(View view,String data);
    }


   public  FoodTypeAdapter(Context context, ArrayList<String> foodTypeList){
        this.context=context;
        this.foodTypeList=foodTypeList;
    }
    public  FoodTypeAdapter(){

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_type_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.food_type.setText(foodTypeList.get(position));
        holder.itemView.setTag(foodTypeList.get(position));
    }


    @Override
    public int getItemCount() {
        return foodTypeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView distance;
        public TextView food_type;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.distance = (TextView) rootView.findViewById(R.id.distance);
            this.food_type = (TextView) rootView.findViewById(R.id.food_type);
        }

    }
}
