package gitmad.bitter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import gitmad.bitter.R;
import gitmad.bitter.model.Comment;
import gitmad.bitter.model.User;
import gitmad.bitter.ui.CommentAdapter;
import gitmad.bitter.ui.DividerItemDecoration;

public class ViewPostActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Intent intent = getIntent();
        String postContent = intent.getStringExtra("postContent");
        String userName = intent.getStringExtra("userName");

        TextView postBody = (TextView) findViewById(R.id.postContent);
        TextView user = (TextView) findViewById(R.id.posterUsername);

        postBody.setText(postContent);
        user.setText(userName);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comments_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext()));

        Comment[] comments = getMockComments();

        RecyclerView.Adapter adapter = new CommentAdapter(comments);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Comment[] getMockComments() {
        String[] commentsText = getResources().getStringArray(R.array.mock_comments);
        User user = new User();
        user.setName("NOTgBurdell");
        Comment[] comments = new Comment[commentsText.length];
        for (int i = 0; i < commentsText.length; i++) {
            comments[i] = new Comment();
            comments[i].setText(commentsText[i]);
            comments[i].setUser(user);
        }
        return comments;
    }
}