package cs456.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "cs456.HelloWorld.MESSAGE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void sayPID(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	String message = "ro120908";
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
}
