package pl.conquerors.app.base;

import android.util.Log;

import androidx.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter <T extends BaseView>  {

    protected T mView;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void setmView(T mView) {
        this.mView = mView;
    }

    public void created() {}
    public void resume() {}
    public void pause() { mCompositeSubscription.clear();}

    public void handleSubscription (@NonNull final Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    protected void handleError(final Throwable error){
        Log.e("TAG", "error", error);

        //TODO RetrofitException

        //TODO mView.hideLoading
    }

    protected void showGeneralError() {
        String title = "Ooops....";
        String message = "Something ";

        showError(title, message);
    }

    protected void showError(final String title, final String message) {
        mView.showError(title, message);
    }

}
