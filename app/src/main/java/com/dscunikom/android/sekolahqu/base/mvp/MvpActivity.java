package com.dscunikom.android.sekolahqu.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.dscunikom.android.sekolahqu.base.ui.BaseActivity;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
