package pl.conquerors.app.view.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.Comment;
import pl.conquerors.app.domain.model.Post;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.entity.CommentEntity;
import pl.conquerors.app.model.entity.PostEntity;
import pl.conquerors.app.model.entity.UserEntity;
import pl.conquerors.app.model.entity.mapper.CommentEntityMapper;
import pl.conquerors.app.model.entity.mapper.PostEntityMapper;
import pl.conquerors.app.model.entity.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends BaseActivity {

    @BindView(R.id.text_view_result)
    TextView mGetResult;

    public static Intent getStartingIntents(Context context){
        return new Intent(context, MyProfileActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //getComments();
        //getPosts();
        //createPost();
        //updatePost();
        //deletePost();

        getUser();
    }

    private void getPosts(){
        Call<List<PostEntity>> call = RestClient.getInstance().getPost(new ArrayList<>(Arrays.asList(2,3,6)),null,null);

        call.enqueue(new Callback<List<PostEntity>>() {
            @Override
            public void onResponse(Call<List<PostEntity>> call, Response<List<PostEntity>> response) {
                if(response.body().isEmpty()){
                    mGetResult.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts = PostEntityMapper.transform(response.body());
                posts.forEach(post -> mGetResult.append(post.toString()));
            }

            @Override
            public void onFailure(Call<List<PostEntity>> call, Throwable t) {
                mGetResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(){
        //Call<List<CommentEntity>> call = RestClient.getInstance().getComments("posts/3/comments");
        Call<List<CommentEntity>> call = RestClient.getInstance().getComments(4);

        call.enqueue(new Callback<List<CommentEntity>>() {
            @Override
            public void onResponse(Call<List<CommentEntity>> call, Response<List<CommentEntity>> response) {
                if(response.body().isEmpty()){
                    mGetResult.setText("Code: "+response.code());
                    return;
                }

                List<Comment> comments = CommentEntityMapper.transform(response.body());

                comments.forEach(comment -> {
                    mGetResult.append(comment.toString());
                });
            }

            @Override
            public void onFailure(Call<List<CommentEntity>> call, Throwable t) {
                mGetResult.setText(t.getMessage());
            }
        });
    }

    private void createPost(){
        //PostEntity post = new PostEntity(23,"New Title","New Text");

        Call<PostEntity> call = RestClient.getInstance().createPost(23,"New Title","New Text");

        call.enqueue(new Callback<PostEntity>() {
            @Override
            public void onResponse(Call<PostEntity> call, Response<PostEntity> response) {
                if(!response.isSuccessful()){
                    mGetResult.setText("Code: "+response.code());
                    return;
                }

                Post postResponse = PostEntityMapper.transform(response.body());

                String content = "";
                content +="Code: "+response.code()+"\n";
                content +=postResponse.toString();

                mGetResult.setText(content);

            }

            @Override
            public void onFailure(Call<PostEntity> call, Throwable t) {
                mGetResult.setText(t.getMessage());
            }
        });

    }

    private void updatePost(){
        PostEntity postEntity = new PostEntity(12,null,"New Text");

        Call<PostEntity> call = RestClient.getInstance().putPost(5,postEntity);

        call.enqueue(new Callback<PostEntity>() {
            @Override
            public void onResponse(Call<PostEntity> call, Response<PostEntity> response) {
                if(!response.isSuccessful()){
                    mGetResult.setText("Code: "+response.code());
                    return;
                }

                Post postResponse = PostEntityMapper.transform(response.body());

                String content = "";
                content +="Code: "+response.code()+"\n";
                content +=postResponse.toString();

                mGetResult.setText(content);
            }

            @Override
            public void onFailure(Call<PostEntity> call, Throwable t) {
                mGetResult.setText(t.getMessage());
            }
        });
    }

    private void deletePost(){
        Call<Void> call = RestClient.getInstance().deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mGetResult.setText("Code: "+ response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mGetResult.setText(t.getMessage());
            }
        });
    }

    private void getUser(){
        Call<UserEntity> call = RestClient.getInstance().getUserByName("Ala");

        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(!response.isSuccessful()){
                    mGetResult.setText("Code: "+response.code());
                    return;
                }

                User user = UserEntityMapper.transform(response.body());

                mGetResult.setText(user.toString());
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                mGetResult.setText(t.getMessage());
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
