package pl.conquerors.app.view.friends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.CollectionViewHolder;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;

public class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> itemsToDisplay = new ArrayList<>();
    private final List<User> friends = new ArrayList<>();

    public FriendsAdapter() { setUpItems();}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        final View view;
        final RecyclerView.ViewHolder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend, viewGroup, false);
        holder = new FriendViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final CollectionViewHolder recycleHolder = (CollectionViewHolder) viewHolder;
        recycleHolder.bind(itemsToDisplay.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    void setItems(final List<User> friends) {
        this.friends.clear();
        this.friends.addAll(friends);
        setUpItems();
        notifyDataSetChanged();
    }

    private void setUpItems() {
        if(!friends.isEmpty()) {
            for (User user : friends) {
                itemsToDisplay.add(new AdapterUser(user));
            }
        }
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder implements CollectionViewHolder<AdapterUser> {

        @BindView(R.id.friend_image)
        ImageView image;

        @BindView(R.id.friend_name)
        TextView name;

        /*@BindView(R.id.friend_characters)
        TextView characters;

        @BindView(R.id.friend_games)
        TextView games;*/

        @OnClick(R.id.item_view)
        void userSelected() {
            //TODO start friend profile by id
            Navigator.startFriendProfile(itemView.getContext(), boundUser);
        }

        private User boundUser;

        FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(final AdapterUser adapterUser) {
            User user = adapterUser.getUser();
            name.setText(user.getUserNick());
            boundUser = user;
        }
    }

    private static class AdapterUser {
        private User user;

        public AdapterUser(final User user) {
            this.user = user;
        }

        public User getUser() { return user; }
    }

}
