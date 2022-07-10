package com.uygulamalarim.random_slem_ve_soru_getiren_uygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleBtnTopla, toggleBtnCarp, toggleBtnCikar, toggleBtnBol;
    private TextView txtSoru;
    private EditText editTxtTahmin;
    private ArrayList<String> islemTurleri;
    private Random rndislem,rndSayi;
    private int rndislemnumber,rndSayiNumber;
    private String soru,tahmin;
    private int s1,s2,sonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        islemTurleri=new ArrayList<>();
        rndislem=new Random();
        rndSayi=new Random();

        toggleBtnTopla=(ToggleButton)findViewById(R.id.toggleBtnTopla);
        toggleBtnCarp=(ToggleButton)findViewById(R.id.toggleBtnCarp);
        toggleBtnCikar=(ToggleButton)findViewById(R.id.toggleBtnCikar);
        toggleBtnBol=(ToggleButton)findViewById(R.id.toggleBtnBol);
        txtSoru=(TextView)findViewById(R.id.txtSoru);
        editTxtTahmin=(EditText)findViewById(R.id.editTxtTahmin);

        toggleBtnTopla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b)
                    islemTurleri.add("+");
                else
                    islemTurleri.remove("+");
            }
        });
        toggleBtnBol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    islemTurleri.add("/");
                else
                    islemTurleri.remove("/");
            }
        });
        toggleBtnCikar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    islemTurleri.add("-");
                else
                    islemTurleri.remove("-");
            }
        });
        toggleBtnCarp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    islemTurleri.add("*");
                else
                    islemTurleri.remove("*");
            }
        });



    }

    public void btnClick(View v){
        switch (v.getId()){
            case R.id.btnSoruGetir:
                txtSoru.setText(islemTuruveSoruBelirle());

                break;
            case R.id.btnSoruCevapla:
                tahminKontrol();
                break;
        }

    }

    private void tahminKontrol(){
        tahmin=editTxtTahmin.getText().toString();
        if (!TextUtils.isEmpty(tahmin)){
            if (tahmin.matches(String.valueOf(sonuc))){
                System.out.println("Tebrikler doğru tahmin");
                editTxtTahmin.setText("");
                txtSoru.setText(islemTuruveSoruBelirle());

            }else
                System.out.println("Tahmin yanlış");
        }else
            System.out.println("Girilen tahmin değeri boş olamaz");
    }

    private String islemTuruveSoruBelirle(){
        soru+="";
        if (islemTurleri.size()>0){
            rndislemnumber=rndislem.nextInt(islemTurleri.size());

            s1=randomSayiGetir();
            soru+=s1;
            soru+=" ";
            soru+=islemTurleri.get(rndislemnumber);
            soru+=" ";
            s2=randomSayiGetir();
            soru+=s2;

            switch (islemTurleri.get(rndislemnumber)){
                case "+":
                    sonuc=s1+s2;
                    break;
                case "-":
                    sonuc=s1-s2;
                    break;
                case "*":
                    sonuc=s1*s2;
                    break;
                case "/":
                    sonuc=s1/s2;
                    break;

            }

        }
        return soru;
    }

    private int randomSayiGetir(){
        rndSayiNumber=rndSayi.nextInt(10)+1;
        return rndSayiNumber;
    }
}