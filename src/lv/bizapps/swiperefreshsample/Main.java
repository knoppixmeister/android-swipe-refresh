package lv.bizapps.swiperefreshsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends ActionBarActivity {
	protected ListView lv;
	protected SwipeRefreshLayout srl;

	@SuppressLint("InlinedApi")
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		srl = (SwipeRefreshLayout)findViewById(R.id.refresh);

		srl.setColorScheme(	android.R.color.holo_blue_bright,
				            android.R.color.holo_green_light,
				            android.R.color.holo_orange_light,
				            android.R.color.holo_red_light);

		srl.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				Log.e("AAA", "SRL ON REFRESH");

				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(5000);
						}
						catch(Exception e) {
							e.printStackTrace();
						}

						runOnUiThread(new Runnable() {
							public void run() {
								srl.setRefreshing(false);
							}
						});
					}
				}).start();
			}
		});

		lv = (ListView)findViewById(R.id.listView);
		lv.setAdapter(new CustomListViewAdapter(this));
	}

	class CustomListViewAdapter extends BaseAdapter {
		protected Context ctx;
		protected LayoutInflater li;

		public CustomListViewAdapter(Context ctx) {
			this.ctx = ctx;
			li = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return 30;
		}

		public Object getItem(int id) {
			return id;
		}

		public long getItemId(int id) {
			return id;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int pos, View view, ViewGroup root) {
			view = li.inflate(R.layout.list_item, root, false);

			TextView tv = (TextView)view.findViewById(R.id.textView1);
			tv.setText(Html.fromHtml(pos+""));

			return view;
		}
	}
}
