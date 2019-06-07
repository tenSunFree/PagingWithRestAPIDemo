package com.home.pagingwithrestapidemo.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;

public class UserDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<UserItemKeyedDataSource> mutableLiveData;

    public UserDataSourceFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        UserItemKeyedDataSource itemKeyedUserDataSource = new UserItemKeyedDataSource();
        mutableLiveData.postValue(itemKeyedUserDataSource);
        return itemKeyedUserDataSource;
    }
}
