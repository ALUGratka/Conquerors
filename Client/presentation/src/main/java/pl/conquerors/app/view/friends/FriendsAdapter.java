package pl.conquerors.app.view.friends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.CollectionViewHolder;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;

public class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> mItemsToDisplay = new ArrayList<>();
    private final List<User> friends = new ArrayList<>();

    public FriendsAdapter() { setUpItems();}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view;
        final RecyclerView.ViewHolder holder;

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend, viewGroup, false);
        holder = new FriendViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final CollectionViewHolder recycleHolder = (CollectionViewHolder) viewHolder;
        recycleHolder.bind(mItemsToDisplay.get(position));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void setItems(final List<User> friends) {
        this.friends.clear();
        this.friends.addAll(friends);
        setUpItems();
        notifyDataSetChanged();
    }

    private void setUpItems() {
        User testUser = new User();
        testUser.setmNick("Tester");
        testUser.setmId(1);

        friends.add(testUser);

        if(!friends.isEmpty()) {
            for (User user : friends) {
                mItemsToDisplay.add(new AdapterUser(user));
            }
        }
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder implements CollectionViewHolder<AdapterUser> {

        @BindView(R.id.friend_image)
        ImageView image;

        @BindView(R.id.friend_name)
        TextView name;

        @BindView(R.id.friend_points)
        TextView points;

        @BindView(R.id.number_of_games)
        TextView numberOfGames;

        @OnClick(R.id.item_view)
        void userSelected() { Navigator.startMyProfile(itemView.getContext()); }

        private long boundUserId;

        FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(final AdapterUser adapterUser) {
            User user = adapterUser.getUser();
            name.setText(user.getmNick());
            boundUserId = user.getmId();
            numberOfGames.setText("0");
            points.setText("123");
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
