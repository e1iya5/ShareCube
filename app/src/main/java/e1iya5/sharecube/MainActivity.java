    package e1iya5.sharecube;

    import android.content.Intent;
    import android.os.Handler;
    import android.os.SystemClock;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.Timer;
    import java.util.TimerTask;

    public class MainActivity extends AppCompatActivity {
        private int az;
        private TextView tVaugenzahl;
        private Button actionButton;
        private Button shareButton;

        final Handler handler = new Handler();

        public MainActivity() {
        }

        private void wuerfeln(){
            this.az = (int) (Math.random() * 6) + 1;
        }

        private void doit(final int i){
            if(i > 5) return;
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            wuerfeln();
                            tVaugenzahl.setText(String.valueOf(az));
                            doit(i+1);
                        }
                    });
                }
            }, 300);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.tVaugenzahl = (TextView) findViewById(R.id.augenzahl);
            this.actionButton = (Button) findViewById(R.id.actionButton);
            this.shareButton = (Button) findViewById(R.id.shareButton);
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doit(1);
                }
        });
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(az == 0){
                        Toast.makeText(getApplicationContext(), "Bitte würfeln Sie erst!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Ich hab diese Augenzahl gewürfelt: "+az+"\n#ShareCube #Hashtag");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });
    }
    }
