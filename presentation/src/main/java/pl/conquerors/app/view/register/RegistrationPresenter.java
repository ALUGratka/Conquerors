package pl.conquerors.app.view.register;

import android.text.TextUtils;

import java.util.Date;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.registration.RegistrationUseCase;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.util.DateUtil;
import pl.conquerors.app.util.UserUtil;
import pl.conquerors.app.util.Validator;

public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    public final static int NICK_MIN = 2;
    public final static int NICK_MAX = 20;
    public final static int PASSWORD_MIN = 6;
    public final static int PASSWORD_MAX = 30;
    public final static int EMAIL_MIN = 5;
    public final static int EMAIL_MAX = 50;

    RegistrationUseCase mUseCase;

    public RegistrationPresenter(final RegistrationUseCase useCase) { mUseCase = useCase; }


    public void performRegistration() {
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
        final Date bornDate = DateUtil.getDateFromDateString(born);
        if (TextUtils.isEmpty(born)) {
            mView.showBornRequired();
            cancel = true;
        } else if (!DateUtil.isDateValid(bornDate)) {
            mView.showBornRequired();
            cancel = true;
        }
        String bornFormatted = DateUtil.getDateHyphenString(bornDate);


        if(cancel) {
            mView.setRegistrationButtonEnabled(true);
        } else {
            mView.showLoading();

            User user;




            if(UserUtil.getNumberOfUsers()==0){
                user = new User(0,nick,born,email,0,1);

                UserUtil.addUser(user);
                mView.onRegistrationSucceeded(email);
            }
            else{
                int id = UserUtil.getNumberOfUsers();
                if(!UserUtil.findIfUserWithEmailExists(email)) {

                    user = new User(id, nick, born, email, 0, 1);

                    UserUtil.addUser(user);
                    mView.onRegistrationSucceeded(email);
                }
                else{
                    mView.hideLoading();
                    mView.showEmailTaken();
                    mView.onRegistrationFiled();
                }
            }



            //mUseCase.setData(nick, email, password, bornFormatted);


            /*handleSubscription(mUseCase.execute().subscribe(new Subscriber<Void>() {
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
            }));*/
            //TODO RegistrationUseCase
        }

    }

}
