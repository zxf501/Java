        implementation 'com.squareup.retrofit2:retrofit:2.5.
        implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
        <uses-permission android:name="android.permission.INTERNET" />
        package com.example.notepad;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.converter.gson.GsonConverterFactory;
 public interface GetApi {
     @GET("users/{user}/repos")
     Call<Repo> getRepo(@Path("user")String user);
}
public interface NotepadService {
    @GET("query")
    Call<NotepadBean> getNotepadInfo(@Query("type") String name,
                                     @Query("hostid") long id);
}
    private static final String BASEURL = "http://www.Notepad501.com/";
    private void downDatas() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.Notepad501.com/")
                .addConverterFactory(GsonConverterFactory.create())//添加转换器工厂
                .build();
        NotepadServiceApiService service = retrofit.create(NotepadServiceApiService.class);
        Call<NotepadBean> call = service.getExpressInfo("zhaoxiaofan", 666798482392L);

        call.enqueue(new Callback<NotepadBean>() {
            @Override
            public void onResponse(Call<NotepadBean> call, Response<NotepadBean> response) {
                if ("200".equals(response.body().getStatus())) {
                    List<NotepadBean.DataBean> beans = response.body().getData();
                    lv.setAdapter(adapter);
                    adapter.setDatas(beans);
                } else {
                    tvErro.setVisibility(View.VISIBLE);
                    tvErro.setText("no message");
                }
            }
            @Override
            public void onFailure(Call<NotepadBean> call, Throwable t) {
                tvErro.setVisibility(View.VISIBLE);
                tvErro.setText(t.getMessage());
            }
        });
    }
}



