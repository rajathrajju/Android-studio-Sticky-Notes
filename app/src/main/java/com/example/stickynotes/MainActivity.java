package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText noteEdt;
    private Button increaseSizeBtn,decreaseSizeBtn,boldBtn,underLineBtn,italicBtn;
    private TextView sizeTV;
    float currentsize;
    @SuppressLint("MissingInflatedId")
    StickyNote note=new StickyNote();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdt =findViewById(R.id.idEdt);
        increaseSizeBtn =findViewById(R.id.idBtnIncrease);
        decreaseSizeBtn =findViewById(R.id.idBtnReduce);
        boldBtn =findViewById(R.id.idBtnBold);
        underLineBtn =findViewById(R.id.idBtnUnderline);
        italicBtn =findViewById(R.id.idBtnItalic);
        sizeTV =findViewById(R.id.idTVSize);
        currentsize= noteEdt.getTextSize();
        sizeTV.setText(""+currentsize);
        increaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTV.setText(""+currentsize);
                currentsize++;
                noteEdt.setTextSize(currentsize);
            }
        });
        decreaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTV.setText(""+currentsize);
                currentsize--;
                noteEdt.setTextSize(currentsize);
            }
        });
        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple));
                if(noteEdt.getTypeface().isBold()){
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    boldBtn.setTextColor(getResources().getColor((R.color.white)));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple));
                }else{
                    boldBtn.setTextColor(getResources().getColor(R.color.purple));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });
        underLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getPaintFlags()==8){
                    underLineBtn.setTextColor(getResources().getColor(R.color.white));
                    underLineBtn.setBackgroundColor(getResources().getColor(R.color.purple));
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags()&(~Paint.UNDERLINE_TEXT_FLAG));

                }else{
                    underLineBtn.setTextColor(getResources().getColor(R.color.purple));
                    underLineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boldBtn.setTextColor(getResources().getColor(R.color.white));
                boldBtn.setBackgroundColor(getResources().getColor(R.color.purple));
                if(noteEdt.getTypeface().isItalic()){
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    italicBtn.setTextColor(getResources().getColor(R.color.white));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple));

                }else{
                    italicBtn.setTextColor(getResources().getColor(R.color.purple));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
            }
        });

    }

    public void saveButton(View view) {
        note.setStick(noteEdt.getText().toString(),this);
        updateWidget();
        Toast.makeText(this, "Note has been Update", Toast.LENGTH_SHORT).show();
    }
    private void updateWidget(){
        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
        RemoteViews remoteViews=new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget = new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVWidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);

    }
}