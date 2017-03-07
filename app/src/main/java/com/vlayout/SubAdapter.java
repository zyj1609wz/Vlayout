package com.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${zhaoyanjun} on 2017/3/7.
 */

public class SubAdapter extends DelegateAdapter.Adapter<SubAdapter.MainViewHolder> {

    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private List<Bean> list  = new ArrayList<>();

    public SubAdapter(Context context, LayoutHelper layoutHelper , List<Bean> list ) {
        this.mContext = context ;
        this.mLayoutHelper = layoutHelper ;
        this.list = list ;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false) ;
        return new MainViewHolder(view );
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        // only vertical
        Bean bean = list.get( position ) ;
        holder.button.setText( bean.getName() ) ;
    }

    @Override
    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
      holder.textView.setText(Integer.toString(offsetTotal));
    }

    @Override
    public int getItemCount() {
        return (list == null ) ? 0 : list.size() ;
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;
        private TextView textView ;
        private Button button ;

        public MainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById( R.id.title );
            button = (Button) itemView.findViewById( R.id.bt );
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
