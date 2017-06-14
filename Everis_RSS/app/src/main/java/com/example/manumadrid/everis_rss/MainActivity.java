package com.example.manumadrid.everis_rss;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


/**
 * Actividad principal que muestra el Feed
 */
public class MainActivity extends AppCompatActivity {

    protected RecyclerView news_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        news_list = (RecyclerView) findViewById(R.id.news_list);
        news_list.setLayoutManager(new LinearLayoutManager(this));

        new GetXMLTask(new GetXMLTask.GetXMLListener() {
            @Override
            public void onReady(News news) {
                final News noticias=news;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AdapterList adapter = new AdapterList(noticias, getApplicationContext());
                        news_list.setAdapter(adapter);
                    }
                });

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
            }
        }).execute();
    }

}
