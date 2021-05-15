package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.np.madpractical.R;

public class MainActivity extends AppCompatActivity {
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Debug","OnCreate");

        Intent receivingEnd = getIntent();
        Bundle bundle = receivingEnd.getExtras();;
        position = receivingEnd.getIntExtra("position",0);
        TextView profileTitle = (TextView) findViewById(R.id.profileTitle);
        TextView profileDescription = (TextView) findViewById(R.id.description);
        Button followBtn = (Button) findViewById(R.id.btnFollow);
        User user;
        if (bundle != null){
            user = bundle.getParcelable("user");
            profileTitle.setText(user.getName());
            profileDescription.setText(user.getDescription());
            followBtn.setText(user.isFollowed()? "Unfollow" : "Follow");
        } else {
            user = new User("Null","Null",0,false);
        }


        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    user.setFollowed(!user.isFollowed());
                    if (user.isFollowed()){
                        followBtn.setText("Unfollow");
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(MainActivity.this, "Followed", duration);
                        toast.show();
                    } else{
                        followBtn.setText("Follow");
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(MainActivity.this, "Unfollowed", duration);
                        toast.show();
                    }
            }
        });
    }
    @Override
    protected void onStart( ) {
        super.onStart();
    }
    @Override
    protected void onResume( ) {
        super.onResume();
        Log.i("Debug","onResume");
    }
    @Override
    protected void onPause( ) {
        super.onPause();
        Log.i("Debug","onPaused");
        ListActivity.uList.get(position).setFollowed(!ListActivity.uList.get(position).isFollowed());
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Debug","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Debug","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Debug","onRestart");
    }
}