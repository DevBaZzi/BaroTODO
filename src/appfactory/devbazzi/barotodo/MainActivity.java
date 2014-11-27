package appfactory.devbazzi.barotodo;

import java.lang.reflect.Field;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	EditText edit;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e5ad00")));
        ab.setDisplayShowHomeEnabled(false);
        
        try {
        	  ViewConfiguration config = ViewConfiguration. get( this);
        	  Field menuKeyField = ViewConfiguration.class .getDeclaredField( "sHasPermanentMenuKey");
        	  if (menuKeyField != null) {
        	     menuKeyField.setAccessible( true );
        	     menuKeyField.setBoolean(config, false );
        	  }
        	} catch (Exception ex) {
        	  ;
        	}
    }
    
    
    public void btnonClick(View v) {
    	
    	edit = (EditText) findViewById(R.id.editText1);
    		
    	if(edit.getText().toString().replace(" ", "").equals("")) {
    		Toast.makeText(this, "할 일을 입력해야 합니다.", Toast.LENGTH_SHORT).show();
    	} else {	
    	
    		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	    PendingIntent pintent = PendingIntent.getActivity(this, 0, new Intent(this, TouchActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
    	
    	    NotificationCompat.Builder notibuilder = new NotificationCompat.Builder(this);
    	    notibuilder.setSmallIcon(R.drawable.ic_launcher)
    	    .setTicker("할 일이 추가되었습니다.")
            .setContentTitle(edit.getText().toString())
            .setContentText("삭제하려면 터치하세요.")
    	    .setContentIntent(pintent)
    	    .setAutoCancel(false)
    	    .setOngoing(true)
    	    .setAutoCancel(true);
    	
    	    int id = new Random(System.currentTimeMillis()).nextInt();
    	
    	    nm.notify(id, notibuilder.build());
    	
    	}
    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, InfoActivity.class);
            startActivity(i);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
