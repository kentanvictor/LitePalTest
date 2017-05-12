package com.example.dell.litepaltest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dell.litepaltest.R;
import com.example.dell.litepaltest.surface.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
        //创建数据库中的表
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vainci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        //添加数据
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Author");
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");
                /**
                 * updateAll()方法中可以指定一个条件约束
                 * 和SQliteDatabase中update()方法的where参数部分有点类似，但更加简洁
                 * 如果不指定数据的话，就会更新全部数据
                * */
               /* book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                book.save();
                book.setPrice(10.99);
                book.save();
                上面这种是最简单最简单，最不用动脑的方法
                */
            }
        });
        //更新数据
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price< ?","15");
                //如果不指定约束条件就意味着你要删除表中的所有数据
            }
        });
        //删除数据
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for(Book book : books)
                {
                    Log.d("MainActivity","book name is " + book.getName());
                    Log.d("MainActivity","book author is " + book.getAuthor());
                    Log.d("MainActivity","book pages is " + book.getPages());
                    Log.d("MainActivity","book price is " + book.getPrice());
                    Log.d("MainActivity","book press is " + book.getPress());
                }
            }
        });
        //使用LitePal查询数据
    }
}
