package ir.mahan.train.view;

public interface PersonTableListener {
	public void rowDeleted(int row);
	public void refresh();
	public void save(int row);
}
