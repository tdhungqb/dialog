package dialog_custom;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qpc1.dialog_custom.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mLvName;
    private TextView mtvLogin;
    private List<String> mArrayNames;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intViews();
        intListOfNames();
        setData();
        mtvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });
    }

    private void intViews() {
        mLvName = findViewById(R.id.lvName);
        mtvLogin = findViewById(R.id.tvLogin);
    }

    private void intListOfNames() {
        mArrayNames = new ArrayList<>();
        mArrayNames.add("Android");
        mArrayNames.add("PHP");
        mArrayNames.add("iOS");
        mArrayNames.add("Java");
    }

    private void setData() {
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mArrayNames);
        mLvName.setAdapter(mAdapter);
        mLvName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDiaLog(position);
                return false;
            }
        });
    }

    private void showDiaLog(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa môn học này không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mArrayNames.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void setLogin() {
        final Dialog dialog = new Dialog(this);
        final EditText edtUser = (EditText) dialog.findViewById(R.id.edtUser);
        final EditText edtPass = (EditText) dialog.findViewById(R.id.edtPass);
        Button btnDangNhap = (Button) dialog.findViewById(R.id.btnDangNhap);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
        dialog.setContentView(R.layout.login_custom);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (user.equals("Hung") && pass.equals("12345")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    Toast.makeText(MainActivity.this, "Sai user or pass", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
