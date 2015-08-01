package ir.mahan.train.view;

import ir.mahan.train.model.User;

public interface IuserListener<T> {
	void stringEmitted(T user);
}
