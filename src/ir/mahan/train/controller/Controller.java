package ir.mahan.train.controller;

import ir.mahan.train.model.AgeCategory;
import ir.mahan.train.model.DataBase;
import ir.mahan.train.model.Person;
import ir.mahan.train.model.User;

public class Controller {
	DataBase db;

	public Controller(DataBase db) {
		this.db = new DataBase();
	}
}
