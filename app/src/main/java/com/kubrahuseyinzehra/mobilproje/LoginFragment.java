package com.kubrahuseyinzehra.mobilproje;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kubrahuseyinzehra.mobilproje.Models.LoginPojo;
import com.kubrahuseyinzehra.mobilproje.RestApi.ApiUtils;
import com.kubrahuseyinzehra.mobilproje.RestApi.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private AppCompatButton buttonLogin;
    private EditText editTextMailLogin;
    private EditText editTextPasswordLogin;

    private RestApi restApi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tasarim= inflater.inflate(R.layout.fragment_login, container, false);
        buttonLogin = tasarim.findViewById(R.id.buttonLogin);
        editTextMailLogin=tasarim.findViewById(R.id.editTextMailLogin);
        editTextPasswordLogin=tasarim.findViewById(R.id.editTextPasswordLogin);
        restApi = ApiUtils.getRestApiInterface();
         buttonLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String ad = editTextMailLogin.getText().toString();
                 String sifre = editTextPasswordLogin.getText().toString();

                 login(ad,sifre,view);

             }
         });
        return tasarim;
    }
    public void login (String ad, String sifre,View view){
       restApi.control(ad,sifre).enqueue(new Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                if(response.body() != null) {
                    if(response.body().getId() != null && response.body().getKadi() !=null) {
                        Log.e("*******", "*******");
                        Log.e("kişi id", response.body().getId());
                        Log.e("kişi ad", response.body().getKadi());
                        Log.e("*******", "*******");
                        Navigation.findNavController(view).navigate(R.id.logindenAnasyfayaGecis);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {

            }
        });
    }
}