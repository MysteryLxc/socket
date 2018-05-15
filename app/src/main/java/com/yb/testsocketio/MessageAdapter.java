package com.yb.testsocketio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12475 on 2018/5/15.
 */

public class MessageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<SocketBean> mList;


    public MessageAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void insert(SocketBean bean){
        int position=mList.size();
        mList.add(bean);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_recycler_view, parent, false));


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.setMsg(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTime;
        private TextView mUserName;
        private TextView mMsg;


        public ViewHolder(View itemView) {
            super(itemView);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mUserName = (TextView) itemView.findViewById(R.id.userName);
            mMsg = (TextView) itemView.findViewById(R.id.msg);
        }

        void setMsg(SocketBean bean, int position) {

            mTime.setText(bean.getTime());
            mUserName.setText(bean.getUser().getUname());
            mMsg.setText(bean.getMsg());
        }
    }
}
