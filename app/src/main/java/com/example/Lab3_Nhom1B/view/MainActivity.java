package com.example.Lab3_Nhom1B.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.Lab3_Nhom1B.DetailActivity;
import com.example.Lab3_Nhom1B.R;
import com.example.Lab3_Nhom1B.adapter.NoteAdapter;
import com.example.Lab3_Nhom1B.adapter.OnItemClickListener;
import com.example.Lab3_Nhom1B.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> noteData;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    FloatingActionButton fabAdd;
    ArrayList<Note> getList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }

    private void init() {
        recyclerView = findViewById(R.id.rv_notes);
        fabAdd = findViewById(R.id.fap_add);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        getList = new ArrayList<>();
        getList.add(new Note("Animal1", "Live in sea", dateFormat.format(new Date()), R.drawable.octopus));
        getList.add(new Note("Animal2", "Live in land", dateFormat.format(new Date()), R.drawable.pig));
        getList.add(new Note("Animal3", "Live in cave, land", dateFormat.format(new Date()), R.drawable.rabit));
        getList.add(new Note("Animal4", "Live in land", dateFormat.format(new Date()), R.drawable.sheep));
        getList.add(new Note("Animal5", "Live in cave,land", dateFormat.format(new Date()), R.drawable.snake));

        noteAdapter = new NoteAdapter(getList, new OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                onClickGotoDetail(note);
            }

            @Override
            public void onItemupdate(Note note, int i) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.update_data);
                EditText edtTitle = dialog.findViewById(R.id.edt_title_update);
                EditText edtimg = dialog.findViewById(R.id.edt_chooseimg_update);
                EditText edtDescriptioin = dialog.findViewById(R.id.edt_description_update);
                Button btnUpdate = dialog.findViewById(R.id.btn_update);
                Button btnDelete = dialog.findViewById(R.id.btn_Delete);

                edtTitle.setText(note.getTitle().toString());
                edtDescriptioin.setText(note.getDescription().toString());
                edtimg.setText(note.getId());


                dialog.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = edtTitle.getText().toString().trim();
                        String des = edtDescriptioin.getText().toString().trim();
                        int animal = 0;
                        String animals = edtimg.getText().toString();
                        switch (animals) {
                            case "res/drawable/sheep.jpg" :
                                animal = R.drawable.sheep;
                                break;
                            case "res/drawable/octopus.jpg" :
                                animal = R.drawable.octopus;
                                break;
                            case "res/drawable/pig.jpg" :
                                animal = R.drawable.pig;
                                break;
                            case "res/drawable/rabit.jpg" :
                                animal = R.drawable.rabit;
                                break;
                            case "res/drawable/snake.jpg" :
                                animal = R.drawable.snake;
                                break;
                        }
                        Note note1 =new Note(title,des,dateFormat.format(new Date()), animal);
                        getList.set(i,note1);
                        noteAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getList.remove(note);
                        noteAdapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });



            }
        });
        recyclerView.setAdapter(noteAdapter);



        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_add);
                EditText edtTitle = dialog.findViewById(R.id.edt_title_add);
                EditText edtimg = dialog.findViewById(R.id.edt_chooseimg);
                EditText edtDescriptioin = dialog.findViewById(R.id.edt_description);
                Button btnSave = dialog.findViewById(R.id.btn_save);
                Button btnCancel = dialog.findViewById(R.id.btn_cancel);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title;
                        String description,animals;
                        int animal = 0;
                        animals = edtimg.getText().toString();
                        switch (animals) {
                            case "sheep" :
                                animal = R.drawable.sheep;
                                break;
                            case "octopus" :
                                animal = R.drawable.octopus;
                                break;
                            case "pig" :
                                animal = R.drawable.pig;
                                break;
                            case "rabit" :
                                animal = R.drawable.rabit;
                                break;
                            case "snake" :
                                animal = R.drawable.snake;
                                break;
                        }

                        title = edtTitle.getText().toString();
                        description = edtDescriptioin.getText().toString();
                        getList.add(new Note(title, description, dateFormat.format(new Date()), animal));

                        noteAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();


            }
        });


    }

    private void onClickGotoDetail(Note note) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Object_user", note);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}