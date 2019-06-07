package com.home.pagingwithrestapidemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.home.pagingwithrestapidemo.data.UserData;
import com.home.pagingwithrestapidemo.paging.UserDataSourceFactory;

public class UserViewModel extends ViewModel {

    public LiveData<PagedList<UserData>> userList;

    public UserViewModel() {
        int pageSize = 20;
        int prefetchDistance = 60;
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setPageSize(pageSize) // 每一頁item數量
                .setPrefetchDistance(prefetchDistance) // 預加載數量, 通常是pageSize的整數倍
                .setEnablePlaceholders(true) // 是否啟用佔位功能
                .build();
        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
        userList = (new LivePagedListBuilder(userDataSourceFactory, pagedListConfig)).build();
    }
}
