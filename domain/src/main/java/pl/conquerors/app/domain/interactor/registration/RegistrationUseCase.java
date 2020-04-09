package pl.conquerors.app.domain.interactor.registration;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import pl.conquerors.app.domain.repository.UserRepository;
import rx.Observable;

public class RegistrationUseCase extends ResultUseCase<Void> {

    private String mNick;
    private String mEmail;
    private String mPassword;
    private String mBorn;

    private UserRepository mUserRepository;

    public RegistrationUseCase(ComposedScheduler scheduler, UserRepository userRepository) {
        super(scheduler);
        mUserRepository = userRepository;
    }

    @Override
    protected Observable<Void> buildUseCaseObservable() {
        //TODO userRepository
        return mUserRepository.registerUser(mNick,mEmail,mPassword,mBorn);
    }

    public void setData(final String nick, final String email, final String password,  final String born) {
        mNick = nick;
        mEmail = email;
        mPassword = password;
        mBorn = born;
    }

    public String getEmail() {return mEmail; }
}
