package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        sharedPreferences = getSharedPreferences("lab5", Context.MODE_PRIVATE);
        EditText editText = findViewById(R.id.editTxtAddNote);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);

        if (noteid != -1) {
            Note note = DisplayNotesActivity.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }


    }

    public void onSaveButtonClick(View view) {
        TextView noteTextView = findViewById(R.id.editTxtAddNote);
        DBHelper dbHelper = new DBHelper(this);

        String title;
        String content = noteTextView.getText().toString();
        String userName = sharedPreferences.getString("username", "");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (DisplayNotesActivity.notes.size() + 1);
            dbHelper.saveNotes(userName, title, content, date);
        }
        else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content);
        }

        Intent intent = new Intent(this, DisplayNotesActivity.class);
        startActivity(intent);
    }
}
