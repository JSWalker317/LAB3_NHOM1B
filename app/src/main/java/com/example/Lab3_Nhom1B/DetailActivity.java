package com.example.Lab3_Nhom1B;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Lab3_Nhom1B.model.Note;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Note note = (Note) bundle.get("Object_user");

        TextView text_title= findViewById(R.id.tv_detail);
        TextView text_des = findViewById(R.id.tv_detail_des);
        ImageView img_detail = findViewById(R.id.img_detail);

        text_title.setText(note.getTitle());
        text_des.setText(note.getDescription());
        img_detail.setImageResource(note.getId());
    }
}