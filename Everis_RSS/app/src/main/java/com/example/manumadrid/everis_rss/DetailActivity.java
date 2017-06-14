package com.example.manumadrid.everis_rss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ManuMadrid on 14/06/2017.
 */

/**
 * Actividad que muestra una version ampliada de la noticia
 */
public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_clicked);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        final String url = intent.getStringExtra("url");
        String image = intent.getStringExtra("image");


        TextView titleLayout = (TextView) findViewById(R.id.title);
        titleLayout.setText(title);
        ImageView imageLayout = (ImageView) findViewById(R.id.imageNew);
        if(image!=null){
            Picasso.with(this).load(image)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.error)
                    .into(imageLayout);
        }else{
            Picasso.with(this).load(R.drawable.error)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.error)
                    .into(imageLayout);
        }

        TextView descriptionLayout = (TextView) findViewById(R.id.description);
        descriptionLayout.setText(description);

        Button button = (Button) findViewById(R.id.url);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


    }
}
