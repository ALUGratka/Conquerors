package pl.conquerors.app.view.friends.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import pl.conquerors.app.base.CollectionViewHolder;
import pl.conquerors.app.domain.model.User;

public class FindFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class FriendViewHolder extends RecyclerView.ViewHolder implements CollectionViewHolder<User> {


        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(User data) {

        }
    }
}
