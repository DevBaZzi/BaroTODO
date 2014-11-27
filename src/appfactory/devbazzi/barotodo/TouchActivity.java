package appfactory.devbazzi.barotodo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class TouchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        
        Toast.makeText(this, "할 일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
