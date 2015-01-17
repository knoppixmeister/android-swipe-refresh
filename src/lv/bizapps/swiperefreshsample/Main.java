package lv.bizapps.swiperefreshsample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {
	protected ListView lv;
	protected SwipeRefreshLayout srl;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		srl = (SwipeRefreshLayout)findViewById(R.id.refresh);
		srl.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				Log.e("AAA", "SRL ON REFRESH");

				srl.setRefreshing(true);

				srl.postDelayed(new Runnable() {
					public void run() {
						srl.setRefreshing(false);
					}
				}, 3000);
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
