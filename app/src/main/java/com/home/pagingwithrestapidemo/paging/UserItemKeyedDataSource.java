package com.home.pagingwithrestapidemo.paging;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.home.pagingwithrestapidemo.data.UserData;
import com.home.pagingwithrestapidemo.http.GitHubApi;
import com.home.pagingwithrestapidemo.http.GitHubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 如果需要使用每N項數據項的數據拉取每N+1項的話, 可以使用ItemKeyedDataSource
 * 比如你在為一個討論型應用拉取螺紋評論, 你可能需要傳遞最後一條評論的ID來獲取下一條評論的內容
 */
@SuppressWarnings("NullableProblems")
public class UserItemKeyedDataSource extends ItemKeyedDataSource<Integer, UserData> {

    private GitHubApi gitHubService;

    UserItemKeyedDataSource() {
        gitHubService = GitHubService.createGitHubService();
    }

    /**
     * 第一頁資料取得
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<UserData> callback) {
        final ArrayList<UserData> gitHubUser = new ArrayList<>();
        int since = 0;
        gitHubService.getUser(since, params.requestedLoadSize).enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                int successCode = 200;
                if (response.isSuccessful() && response.code() == successCode) {
                    gitHubUser.addAll(response.body());
                    callback.onResult(gitHubUser);
                } else {
                    Log.d("more", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                String errorMessage = t.getMessage();
                Log.d("more", errorMessage);
            }
        });
    }

    /**
     * 後續下一頁資料取得
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<UserData> callback) {
        final List<UserData> gitHubUser = new ArrayList<>();
        gitHubService.getUser(params.key, params.requestedLoadSize).enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                if (response.isSuccessful()) {
                    gitHubUser.addAll(response.body());
                    callback.onResult(gitHubUser);
                } else {
                    Log.d("more", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                String errorMessage = t.getMessage();
                Log.d("more", errorMessage);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<UserData> callback) {
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull UserData item) {
        return item.getId();
    }
}
