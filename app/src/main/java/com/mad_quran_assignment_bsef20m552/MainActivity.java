package com.mad_quran_assignment_bsef20m552;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView surahListView;
    private TextView surahTextView;
    private QDH qdh;
    private QuranArabicText quranArabicText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surahListView = findViewById(R.id.surah_list);
        surahTextView = findViewById(R.id.surah_text);

        qdh = new QDH();
        quranArabicText = new QuranArabicText();

        List<String> surahNames = qdh.GetSurahNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, surahNames);
        surahListView.setAdapter(adapter);

        surahListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int surahNumber = position + 1;
                int startAyat = qdh.getSurahStart(surahNumber) - 1;
                int endAyat = startAyat + qdh.getSurahVerses(surahNumber);

                String[] surahContent = quranArabicText.GetData(startAyat, endAyat);
                StringBuilder stringBuilder = new StringBuilder();
                for (String ayat : surahContent) {
                    stringBuilder.append(ayat).append("\n");
                }
                surahTextView.setText(stringBuilder.toString());
            }
        });
    }
}



