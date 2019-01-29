package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.MyAdapter;
import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * 大作业:实现一个抖音消息页面,所需资源已放在res下面
 */
public class Exercises3 extends AppCompatActivity {

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    List<Message> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        recyclerView = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        try{
            InputStream inputStream = getAssets().open("data.xml");
            messages = PullParser.pull2xml(inputStream);
            myAdapter = new MyAdapter(messages);
            //recyclerView.setAdapter(myAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastCompleteVisableItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visiableitemcount = layoutManager.getChildCount();
                int totalitemcount = layoutManager.getItemCount();
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    if(visiableitemcount > 0 &&lastCompleteVisableItemPosition >= totalitemcount-1){
                        Toast.makeText(Exercises3.this, "已达底部",Toast.LENGTH_SHORT).show();
                    }
                }
            }

//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                if(layoutManager instanceof LinearLayoutManager){
//                    lastCompleteVisableItemPosition = ((LinearLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
//                }
//            }
        });
    }

}
