package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    public void onLoginClick(View view) {
        EditText txtField = findViewById(R.id.editTxtUsername);
        String userName = txtField.getText().toString();

        sharedPreferences.edit().putString("username", userName).apply();

        Intent intent = new Intent(this, DisplayNotesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("lab5", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("username", "");

        if (!userName.equals("")) {
            Intent intent = new Intent (this, DisplayNotesActivity.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }
}
