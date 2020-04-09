package pl.conquerors.app.domain.interactor.registration;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import rx.Observable;

public class RegistrationUseCase extends ResultUseCase<Void> {

    private String mNick;
    private String mEmail;
    private String mPassword;
    private String mBorn;

    public RegistrationUseCase(ComposedScheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        //TODO userRepository


        return null;
    }

    public void setData(final String nick, final String email, final String password,  final String born) {
        mNick = nick;
        mEmail = email;
        mPassword = password;
        mBorn = born;
    }

    public String getEmail() {return mEmail; }
}
