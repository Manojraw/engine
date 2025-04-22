package com.example.myapplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.igdb.com/v4/";

        // Replace with real URL
        private static Retrofit retrofit;


    public static IGDBApi getApi() {

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            if (retrofit == null) {


                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)  // sirf client add karo
                        .build();

            }
        return retrofit.create(IGDBApi.class);
        }


    }

