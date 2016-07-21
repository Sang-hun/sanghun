package com.example.user.cmoney;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class List extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            setContentView(R.layout.list);

            addToolBar();

            ArrayList<ExpenseListModel> list = new ArrayList<>();
            list.add(new ExpenseListModel("엉터리생고기1", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기2", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기3", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기4", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기5", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기6", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기7", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기8", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기9", "승인대기", "홍길동외 1인", "201601701", "15600"));
            list.add(new ExpenseListModel("엉터리생고기10", "승인대기", "홍길동외 1인", "201601701", "15600"));

            ListView listView = (ListView) findViewById(R.id.list);
            ExpenseListAdapter adapter = new ExpenseListAdapter(this);
            adapter.setList(list);
            listView.setAdapter(adapter);

        } catch (Exception e) {

        }
    }

    public void toolbarRightButtonClick(View view) {
        Intent intent = new Intent(this, Please.class);
        startActivity(intent);
    }

    private void addToolBar() throws Exception {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.list);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    class ExpenseListModel {
        public ExpenseListModel(String _paymentStoreName, String _status, String _summary, String _paymentDate, String _paymentAmount) {
            paymentStoreName = _paymentStoreName;
            status = _status;
            summary = _summary;
            paymentDate = _paymentDate;
            paymentAmount = _paymentAmount;
        }
        public String paymentStoreName;
        public String status;
        public String summary;
        public String paymentDate;
        public String paymentAmount;
    }

    class ExpenseListAdapter extends BaseAdapter {
        private Context context;
        private java.util.List<ExpenseListModel> lists;
        public ExpenseListAdapter(Context ctx ) {
            context = ctx;
        }
        public void setList(java.util.List list) {
            lists = list;
        }
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int i) {
            return lists.get(i);
        }

        @Override
        public long getItemId(int i) {
            //return lists.get(i);
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(context, R.layout.list_single, null);
            }

            TextView tvSummary = (TextView)view.findViewById(R.id.tv_Summary);
            TextView tvPaymentStoreName = (TextView) view.findViewById(R.id.tv_PaymentStoreName);
            TextView tvStatus = (TextView) view.findViewById(R.id.tv_status);
            TextView tvPaymentDate = (TextView) view.findViewById(R.id.tv_PaymentDate);
            TextView tvPaymentAmount = (TextView) view.findViewById(R.id.tv_PaymentAmount);

            tvSummary.setText(lists.get(i).summary);
            tvPaymentStoreName.setText(lists.get(i).paymentStoreName);
            tvStatus.setText(lists.get(i).status);
            tvPaymentDate.setText(lists.get(i).paymentDate);
            tvPaymentAmount.setText(lists.get(i).paymentAmount);

            return view;
        }
    }

}
