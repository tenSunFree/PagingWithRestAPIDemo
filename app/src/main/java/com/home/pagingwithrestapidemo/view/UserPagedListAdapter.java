package com.home.pagingwithrestapidemo.view;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.home.pagingwithrestapidemo.R;
import com.home.pagingwithrestapidemo.data.UserData;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserPagedListAdapter extends PagedListAdapter<UserData, RecyclerView.ViewHolder> {

    /**
     * 主要是用於RecyclerView的局部更新, 從而提高頁面刷新效率
     */
    private static DiffUtil.ItemCallback<UserData> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserData>() {

        /**
         * 當areItemsTheSame() 返回為false時, 不管areContentsTheSame() 是否為true, adapter中的條目都會更新
         * @return 返回值表示新數據傳入時, 這兩個位置的數據是否是同一個條目
         */
        @Override
        public boolean areItemsTheSame(@NonNull UserData oldItem, @NonNull UserData newItem) {
            return oldItem.getId() == newItem.getId();
        }

        /**
         *
         * @return 返回值表示新舊位置的數據內容是否相同, 這個方法在areItemsTheSame() 返回true時生效
         */
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull UserData oldItem, @NonNull UserData newItem) {
            return oldItem.equals(newItem);
        }
    };

    private Context context;

    UserPagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_main_recycler_view_item, parent, false);
        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserItemViewHolder) holder).bindTo(getItem(position), position);
    }

    class UserItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView login, id;
        View view;

        UserItemViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.circleImageView);
            id = itemView.findViewById(R.id.id);
            login = itemView.findViewById(R.id.login);
            view = itemView.findViewById(R.id.view);
        }

        @SuppressLint("SetTextI18n")
        void bindTo(UserData user, int position) {
            Glide.with(context).load(user.getAvatar_url()).into(circleImageView); // 設置頭像
            String idTitle = "ID： ";
            id.setText(idTitle + user.getId()); // 設置id
            String loginTitle = "Login： ";
            login.setText(loginTitle + user.getLogin()); // 設置login
            int firstPosition = 0;
            if (firstPosition == position) { // 讓每個Item都能保持一致的留白
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }
}
