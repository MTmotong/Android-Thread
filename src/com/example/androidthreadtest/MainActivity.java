package com.example.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {

	public static final int UPDATE_TEXT = 1;
	private TextView text;
	private Button changeText;
	
	private Handler handler = new Handler() {
	
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TEXT:
				//在这里可以进行UI操作
				text.setText("Nice to meet you");
				break;
			default:
				break;
			}
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.change_text:
			new Thread(new Runnable() {
				//在子线程中执行耗时任务，根据任务的执行结果来更新相应的UI控件
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					text.setText("Nice to meet you");
					Message message = new Message();
					message.what = UPDATE_TEXT;
					handler.sendMessage(message); //将Message对象发送出去
				}
			}).start();
			break;
		default:
			break;
		}
	}
}
