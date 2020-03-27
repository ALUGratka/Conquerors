package pl.conquerors.app.view.register;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.registration.RegistrationUseCase;
import pl.conquerors.app.util.Validator;
import rx.Subscriber;

public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    public final static int NICK_MIN = 2;
    public final static int NICK_MAX = 20;
    public final static int PASSWORD_MIN = 6;
    public final static int PASSWORD_MAX = 30;
    public final static int EMAIL_MIN = 5;
    public final static int EMAIL_MAX = 50;

    RegistrationUseCase mUseCase;


    public RegistrationPresenter(final RegistrationUseCase useCase) { mUseCase = useCase; }

    public void performRegistration() throws ParseException {
        mView.setRegistrationButtonEnabled(false);

        mView.hideNickError();
        mView.hideEmailError();
        mView.hidePasswordError();
        mView.hidePasswordConfirmationError();
        mView.hideBornError();

        final String nick = mView.getNick();
        final String email = mView.getEmail();
        final String password = mView.getPassword();
        final String confirmation = mView.getPasswordConfirmation();
        final String born = mView.getBorn();
        boolean cancel = false;

        //check if nick is valid ->Validator class in data package
        if(TextUtils.isEmpty(nick)) {
            mView.showNickRequired();
            cancel = true;
        } else if (!Validator.isLengthValid(nick,NICK_MIN,NICK_MAX)){
            mView.showNickLengthInvalid(NICK_MIN, NICK_MAX);
            cancel = true;
        }

        //check if email is valid
        if(TextUtils.isEmpty(email)) {
            mView.showEmailRequired();
            cancel = true;
        }else if(!Validator.isEmailValid(email)) {
            mView.showEmailInvalid();
            cancel = true;
        }else if(!Validator.isLengthValid(email, EMAIL_MIN, EMAIL_MAX)) {
            mView.showEmailLengthInvalid(EMAIL_MIN, EMAIL_MAX);
            cancel = true;
        }

        //check if valid password && confirmation
        if(!Validator.isLengthValid(password,PASSWORD_MIN, PASSWORD_MAX)) {
            mView.showPasswordLengthInvalid(PASSWORD_MIN,PASSWORD_MAX);
            cancel = true;
            if(!Validator.isLengthValid(confirmation,PASSWORD_MIN,PASSWORD_MAX)) {
                mView.showPasswordConfirmationLengthInvalid(PASSWORD_MIN,PASSWORD_MAX);
            }
        }else if(!TextUtils.equals(password,confirmation)) {
            mView.showPasswordsDoNotMatch();
            cancel = true;
        }

        //check if valid born
        final Date bornDate = new SimpleDateFormat().parse(born);
        if(TextUtils.isEmpty(born)) {
            mView.showBornRequired();
            cancel = true;
        }else if(bornDate == null || !bornDate.before(new Date(Long.MAX_VALUE))) {
            mView.showBornRequired();
            cancel = true;
        }
        String bornFormatted = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(bornDate);

        if(cancel) {
            mView.setRegistrationButtonEnabled(true);
        } else {
            mView.showLoading();

            mUseCase.setData(nick, email, password, bornFormatted);

            handleSubscription(mUseCase.execute().subscribe(new Subscriber<Void>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mView.hideLoading();
                    mView.setRegistrationButtonEnabled(true);

                    handleError(e);
                }

                @Override
                public void onNext(Void response) {
                    mView.onRegistrationSucceeded(email);
                }
            }));
            //TODO RegistrationUseCase
        }

    }

}
