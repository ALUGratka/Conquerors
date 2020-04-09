package pl.conquerors.app.domain.interactor.login;

import pl.conquerors.app.domain.executor.ComposedScheduler;
import pl.conquerors.app.domain.interactor.ResultUseCase;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.domain.repository.UserRepository;
import rx.Observable;

public class LoginUseCase extends ResultUseCase<User> {

    private String mLogin;
    private String mPassword;
    private String mDeviceToken;
    private String mDeviceLanguage;

    private UserRepository mUserRepository;

    public LoginUseCase(ComposedScheduler scheduler, UserRepository userRepository) {
        super(scheduler);
        mUserRepository = userRepository;
    }

    @Override
    protected Observable<User> buildUseCaseObservable() {
        return mUserRepository.loginUser(mLogin, mPassword, mDeviceToken, mDeviceLanguage);
    }

    public void setCredentials(final String login, final String password, final String deviceToken, final String deviceLanguage) {
        mLogin = login;
        mPassword = password;
        mDeviceToken = deviceToken;
        mDeviceLanguage = deviceLanguage;
    }

    public String getLogin() {
        return mLogin;
    }
}
