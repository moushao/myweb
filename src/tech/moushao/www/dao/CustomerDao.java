package tech.moushao.www.dao;

import java.util.List;

import tech.moushao.www.bean.Article;
import tech.moushao.www.bean.User;

public interface CustomerDao {

	List<Article> findAll();

	void save(Article c);

	void del(Integer customerId);

	void update(Article c);

	Article findOne(Integer customerId);

}
