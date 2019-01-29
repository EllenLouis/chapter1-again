package chapter.android.aweme.ss.com.homework.model;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Message> messageList;
    private int mNumberItems;

    public MyAdapter(List<Message> messages) {
        messageList = messages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Message message = messageList.get(i);
        myViewHolder.updateUI(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_title;
        private final TextView tv_info;
        private final TextView tv_time;
        private final ImageView official_notice;
        private final chapter.android.aweme.ss.com.homework.widget.CircleImageView tv_avatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_info = itemView.findViewById(R.id.tv_description);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_avatar = itemView.findViewById(R.id.iv_avatar);
            official_notice = itemView.findViewById(R.id.robot_notice);
            //itemView.setOnClickListener((View.OnClickListener) this);
        }

        public void updateUI(Message message) {
            tv_title.setText(message.getTitle());
            tv_info.setText(message.getDescription());
            tv_time.setText(message.getTime());
            tv_avatar.setImageResource(getIconName(message));
            if (message.isOfficial() == true) {
                official_notice.setVisibility(View.VISIBLE);
            } else official_notice.setVisibility(View.GONE);

        }

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public int getIconName(Message message) {
        String string = message.getIcon().toString();
        if (string.equals("TYPE_ROBOT"))
            return R.drawable.session_robot;
        else if (string.equals("TYPE_GAME"))
            return R.drawable.icon_micro_game_comment;
        else if (string.equals("TYPE_SYSTEM"))
            return R.drawable.session_system_notice;
        else if (string.equals("TYPE_STRANGER"))
            return R.drawable.session_stranger;
        else
            return R.drawable.icon_girl;
    }
}


