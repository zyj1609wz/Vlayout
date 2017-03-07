package com.vlayout;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter myDelegateAdapter ;
    private List<Bean> beenList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });

        //设置回收复用池大小
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

       // LayoutHelper helper = new LinearLayoutHelper( ) ;

        StickyLayoutHelper helper = new StickyLayoutHelper() ;
        helper.setOffset(100);
        helper.setAspectRatio(4);

        getData();
        myDelegateAdapter = new SubAdapter( this , helper , beenList   ) ;

        recyclerView.setAdapter( myDelegateAdapter );
    }

    private void getData(){
        for ( int i = 0 ; i < 40 ; i++ ){
            Bean bean = new Bean() ;
            bean.setName( "数据" + i );
            beenList.add( bean ) ;
        }
    }




}
