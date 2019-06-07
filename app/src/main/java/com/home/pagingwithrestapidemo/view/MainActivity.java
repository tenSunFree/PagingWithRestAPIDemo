package com.home.pagingwithrestapidemo.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.home.pagingwithrestapidemo.R;
import com.home.pagingwithrestapidemo.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserPagedListAdapter userUserAdapter = initializeViewModel();
        initializeRecyclerView(userUserAdapter);
    }

    /**
     * 初始化ViewModel
     */
    private UserPagedListAdapter initializeViewModel() {
        UserViewModel viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        UserPagedListAdapter userUserAdapter = new UserPagedListAdapter(this);
        viewModel.userList.observe(this, userUserAdapter::submitList); // 監聽userList的數據變化
        return userUserAdapter;
    }

    /**
     * 初始化RecyclerView
     */
    @SuppressLint("WrongConstant")
    private void initializeRecyclerView(UserPagedListAdapter userUserAdapter) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userUserAdapter);
    }
}

