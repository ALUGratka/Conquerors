package pl.conquerors.app.view.showCharacters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class ShowCharactersActivity extends BaseActivity implements ShowCharactersView{

    @BindView(R.id.no_characters_layout)
    LinearLayout noCharactersLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @OnClick(R.id.no_characters_button)
    protected void onNoCharacterButtonClicked(){
        Navigator.startCreateCharacter(this);
        finish();
    }

    private ShowCharactersAdapter showCharactersAdapter;
    private ShowCharactersPresenter mCharacterPresenter;

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, ShowCharactersActivity.class);
    }

    @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_characters);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            showCharactersAdapter = new ShowCharactersAdapter();
            recyclerView.setAdapter(showCharactersAdapter);

            CreateCharacterUseCase characterUseCase = new CreateCharacterUseCase(new AndroidComposedScheduler());

            mCharacterPresenter = new ShowCharactersPresenter(characterUseCase);

            mCharacterPresenter.setmView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCharacterPresenter.resume();
    }

    @Override
    public int getUserId() {
        return (int)SharedPreferenceUtil.getUser(this.getContext()).getUserId();
    }

    @Override
    public void startCreateCharacter(boolean visible) {
        noCharactersLayout.setVisibility(visible?View.VISIBLE:View.GONE);
    }

    @Override
    public void showCharacters(boolean visible) {
        recyclerView.setVisibility(visible? View.VISIBLE:View.GONE);
    }

    public void showCharacters(List<Character> characters){
        showCharactersAdapter.setItems(characters);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
