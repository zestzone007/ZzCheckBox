package fun.zestzone.zzcheckbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ZzCheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = findViewById(R.id.zz);
        findViewById(R.id.btn_change_state).setOnClickListener(this);
        findViewById(R.id.btn_change_enable_state).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_change_state) {
            if (checkBox.isEnabled()) {
                checkBox.setChecked(!checkBox.isChecked());
            } else {
                Toast.makeText(this, "checkBox unable", Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == R.id.btn_change_enable_state) {
            checkBox.setEnabled(!checkBox.isEnabled());
        }
    }
}
